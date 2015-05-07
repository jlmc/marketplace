/**
 *
 */
package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.repository.daos.RequisitionsRepository;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class CheckoutRequisitionService is a Business class that have the responsibility for
 * 'issuing the request '.
 * When checkout method is call the following tasks will be done:
 * <ol>
 * <li>persists Requisition</li>
 * <li>issue the Requisition [ for changing the status to Isseue ]</li>
 * <li>Stock update , taking into account the request requisitionLines</li>
 * </ol>
 * @author xine
 */
@Default
public class CheckoutRequisitionService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisition service. */
    @Inject
    private RequisitionService requisitionService;

    /** The stock service. */
    @Inject
    private StockService stockService;

    /** The requisitions repository. */
    @Inject
    public RequisitionsRepository requisitionsRepository;

    /**
     * Checkout.
     * @param requisition
     *            the requisition
     * @return the requisition
     */
    @Transactional
    public Requisition checkout(final Requisition requisition) {

        Requisition req = this.requisitionService.save(requisition);

        // requisition != null && requisition.id != null
        if (req.isNotIssueble()) {
            throw new BusinessException("The Requisition can't be issue with the Status: "
                    + requisition.getStatus().getDescription());
        }

        // down Stock
        this.stockService.updateStock(req);

        req.setStatus(RequisitionStatus.ISSUED);

        req = this.requisitionsRepository.save(req);

        return req;
    }
}
