package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.repository.daos.RequisitionsRepository;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * The Class StockService.
 */
public class StockService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Inject
    private RequisitionsRepository repository;

    @SuppressWarnings("boxing")
    public void updateStock(final Requisition requisition) {
        // this is importante because the requisition in param may be out of date
        final Requisition req = this.repository.getById(requisition.getId());

        for (final RequisitionItem item : req.getRequisitionItens()) {

            final int aux = item.getProduct().getStockQty() - item.getQty();

            if (aux < 0) {
                // Business RULE: QTY Stock must be gretter or equal than ZERO
                throw new BusinessException(
                        "The Stock is unable to satifazer the request of the product '"
                                + item.getProduct().getSku() + "'");
            }

            item.getProduct().setStockQty(aux);

        }
    }
}
