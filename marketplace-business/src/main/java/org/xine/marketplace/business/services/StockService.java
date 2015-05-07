package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.repository.daos.RequisitionsRepository;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class StockService.
 * have the responsability manager the Stock.
 */
@Default
public class StockService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The repository. */
    @Inject
    private RequisitionsRepository repository;

    /**
     * Update stock.
     * @param requisition
     *            the requisition
     */
    @SuppressWarnings("boxing")
    @Transactional
    public void updateStock(final Requisition requisition) {
        // this is importante because the requisition in param may be out of date
        final Requisition req = this.repository.getById(requisition.getId());

        for (final RequisitionItem item : req.getRequisitionItens()) {

            final int newStockQty = item.getProduct().getStockQty() - item.getQty();

            if (newStockQty < 0) {
                // Business RULE: QTY Stock must be gretter or equal than ZERO
                throw new BusinessException("The Stock is unable to satifazer the " + item.getQty()
                        + "  request of the product '" + item.getProduct().getSku() + "'");
            }

            item.getProduct().setStockQty(newStockQty);

        }
    }

    /**
     * Restore requisition lines.
     * @param requisition
     *            the requisition
     */
    @SuppressWarnings("boxing")
    @Transactional
    public void restoreRequisitionLines(final Requisition requisition) {
        // this is importante because the requisition in param may be out of date
        final Requisition req = this.repository.getById(requisition.getId());

        for (final RequisitionItem item : req.getRequisitionItens()) {
            Integer stockQty = 0;

            stockQty = item.getQty() + item.getProduct().getStockQty();

            item.getProduct().setStockQty(stockQty);
        }

    }
}
