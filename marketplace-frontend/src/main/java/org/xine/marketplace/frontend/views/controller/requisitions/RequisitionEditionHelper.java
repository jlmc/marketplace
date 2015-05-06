package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;

/**
 * The Class RequisitionEditionHelper.
 */
public final class RequisitionEditionHelper {

    /** The Constant EDITABLE_LINE_INDEX. */
    private static final int EDITABLE_LINE_INDEX = 0;

    /**
     * Instantiates a new requisition edition helper.
     */
    private RequisitionEditionHelper() {
    }

    /**
     * Adds the empty requisition item.
     * @param requisition
     *            the requisition
     */
    protected static void addEmptyRequisitionItem(final Requisition requisition) {
        if (requisition != null && requisition.isBudget()) {
            final Product product = new Product();
            final RequisitionItem requisitionItem = new RequisitionItem();
            requisitionItem.setQty(Integer.valueOf(1));

            requisitionItem.setProduct(product);
            requisitionItem.setRequisition(requisition);
            requisition.getRequisitionItens().add(EDITABLE_LINE_INDEX, requisitionItem);
        }
    }

    /**
     * Removes the emptyrequisition item.
     * @param requisition
     *            the requisition
     */
    protected static void removeEmptyRequisitionItem(final Requisition requisition) {
        if (requisition != null && requisition.isBudget()) {

            final RequisitionItem firtsItems = getEditableRequisitionItem(requisition);
            if (firtsItems != null && (firtsItems.getProduct() == null || firtsItems.getProduct().getId() == null)) {
                requisition.getRequisitionItens().remove(EDITABLE_LINE_INDEX);
            }

        }
    }

    /**
     * Gets the editable requisition item.
     * @param requisition
     *            the requisition
     * @return the editable requisition item
     */
    protected static RequisitionItem getEditableRequisitionItem(final Requisition requisition) {
        if (requisition != null && requisition.isBudget()) {
            return requisition.getRequisitionItens().get(EDITABLE_LINE_INDEX);
        }
        return null;
    }

}
