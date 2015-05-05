package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.ClientFilter;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.model.filters.UserFilter;
import org.xine.marketplace.repository.daos.ClientsRepository;
import org.xine.marketplace.repository.daos.RequisitionsRepository;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class RequisitionService.
 */
@Default
public class RequisitionService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisitions repository. */
    @Inject
    private RequisitionsRepository requisitionsRepository;
    /** The clients repository. */

    @Inject
    private ClientsRepository clientsRepository;

    /** The seller repository. */
    @Inject
    private UsersRepository sellersRepository;

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Requisition> search(final RequisitionFilter filter) {
        return this.requisitionsRepository.search(filter);
    }

    /**
     * Save.
     * @param requisition
     *            the requisition
     * @return the requisition
     */
    @Transactional
    public Requisition save(final Requisition requisition) {
        if (requisition != null) {
            if (requisition.getId() == null) {
                requisition.setCreationDate(Date.from(Instant.now()));
                requisition.setStatus(RequisitionStatus.BUDGET);
            }

            return this.requisitionsRepository.save(requisition);
        }
        throw new BusinessException("Can't save that requisition.");
    }

    // ----------------------------------------------------------------------

    /**
     * Gets the sellers.
     * @return the sellers
     */
    public List<User> getSellers() {

        return this.sellersRepository.search(null);
    }

    /**
     * Search client by name.
     * @param clientName
     *            the client name
     * @return the list
     */
    public List<Client> searchClientByName(final String clientName) {
        return this.clientsRepository.shearch(new ClientFilter(null, clientName));
    }

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @return the by id
     */
    public Requisition getById(final Long id) {
        return this.requisitionsRepository.getById(id);
    }

    /**
     * Search seller by name.
     * @param sellerName
     *            the seller name
     * @return the list
     */
    public List<User> searchSellerByName(final String sellerName) {
        return this.sellersRepository.search(new UserFilter(sellerName, null));
    }

    /**
     * Calc totals.
     * @param requisition
     *            the requisition
     * @return the requisition
     */
    public Requisition calcTotals(final Requisition requisition) {
        if (requisition != null) {
            BigDecimal total = BigDecimal.ZERO;
            total = total.add(requisition.getShippingValue())
                    .subtract(requisition.getRebateValue());

            for (final RequisitionItem item : requisition.getRequisitionItens()) {
                if (item.getProduct() != null && item.getProduct().getId() != null) {
                    total = total.add(item.getTotalvalue());
                }
            }

            requisition.setTotalValue(total);
        }
        return requisition;
    }
}