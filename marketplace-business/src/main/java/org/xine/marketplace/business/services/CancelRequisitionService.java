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
 * The Class CancelRequisitionService.
 */
@Default
public class CancelRequisitionService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisitions repository. */
    @Inject
    private RequisitionsRepository requisitionsRepository;

    /** The stock service. */
    @Inject
    private StockService stockService;

    /**
     * Cancel.
     * @param requisition
     *            the requisition
     * @return the requisition
     */
    @Transactional
    public Requisition cancel(final Requisition requisition) {
        // do the next line because the parameters could not be managed yet
        final Requisition req = this.requisitionsRepository.getById(requisition.getId());

        if (req.isNotCancelable()) {
            throw new BusinessException("Requisition can not be canceled in the state "
                    + req.getStatus().getDescription() + ".");
        }

        if (req.isIssued()) {
            this.stockService.restoreRequisitionLines(req);
        }

        req.setStatus(RequisitionStatus.CANCELLED);

        return this.requisitionsRepository.save(req);
    }

}
