package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.RequisitionService;
import org.xine.marketplace.frontend.views.util.helpers.Strings;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.PaymentMethod;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.validator.constraints.SKU;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class RequisitionSaverBean.
 */
@Named
@ViewScoped
public class RequisitionSaverBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    //
    // business services
    //
    // -------------------------------------------------------------------------
    /** The requisition service. */
    @Inject
    private RequisitionService requisitionService;

    // -------------------------------------------------------------------------
    //
    // Model properties
    //
    // -------------------------------------------------------------------------
    /** The requisition. */
    private Requisition requisition;

    /** The editable line product. */
    private Product editableLineProduct;

    /** The sku. */
    private String sku;

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------

    /**
     * Post construct.
     */
    @PostConstruct
    public void postConstruct() {
        clean();
    }

    /**
     * Initialize.
     * @param e
     *            the e
     */
    public void initialize(final ComponentSystemEvent e) {
        if (FacesUtil.isNotPostback()) {

            if (this.requisition == null) {
                clean();
            }

            addEmptyRequisitionItem();

            calcTotals();
        }
    }

    /**
     * Clean.
     */
    private void clean() {
        this.requisition = new Requisition();
        addEmptyRequisitionItem();
    }

    // -------------------------------------------------------------------------
    //
    // Handlers
    //
    // -------------------------------------------------------------------------

    /**
     * Calc totals.
     */
    public void calcTotals() {
        this.requisition = this.requisitionService.calcTotals(this.requisition);
    }

    /**
     * Save.
     */
    public void save() {
        this.requisition = this.requisitionService.save(this.requisition);
        FacesUtil.addInfoMessage("The requisition has been saved successfully!");
    }

    /**
     * Complete client.
     * @param clientName
     *            the client name
     * @return the list
     */
    public List<Client> completeClient(final String clientName) {
        return this.requisitionService.searchClientByName(clientName);
    }

    /**
     * Complete seller.
     * @param sellerName
     *            the seller name
     * @return the list
     */
    public List<User> completeSeller(final String sellerName) {
        return this.requisitionService.searchSellerByName(sellerName);
    }

    /**
     * Complete product.
     * @param productName
     *            the product name
     * @return the list
     */
    public List<Product> completeProduct(final String productName) {
        return this.requisitionService.searchProductByName(productName);
    }

    /**
     * Load editable item product.
     */
    public void loadEditableItemProduct() {
        final RequisitionItem item = getEditableRequisitionItem();
        if (item != null && this.editableLineProduct != null) {

            // if the product exists on the list do nothing
            final Optional<RequisitionItem> exists = this.requisition.getRequisitionItens()
                    .stream().filter(ri -> {
                        return this.editableLineProduct.equals(ri.getProduct());
                    }).findAny();

            if (exists.isPresent()) {
                FacesUtil.addErrorMessage("requisition-form-msg", String.format(
                        "The Product '%s' is already in your the List!",
                        this.editableLineProduct.getName()));
            } else {

                item.setProduct(this.editableLineProduct);
                item.setUnitValue(this.editableLineProduct.getUnitValue());

                addEmptyRequisitionItem();
                this.editableLineProduct = null;
                this.sku = null;

                calcTotals();
            }
        }
    }

    /**
     * Load product by code.
     */
    public void loadProductByCode() {
        if (Strings.isNotNullOrBlank(this.sku)) {
            this.editableLineProduct = this.requisitionService.searchProductByCode(this.sku);
            loadEditableItemProduct();
        }
    }

    // -------------------------------------------------------------------------
    //
    // Getters and Setters
    //
    // -------------------------------------------------------------------------

    /**
     * Gets the payment methods.
     * @return the payment methods
     */
    @SuppressWarnings("static-method")
    public PaymentMethod[] getPaymentMethods() {
        return PaymentMethod.values();
    }

    /**
     * Gets the requisition.
     * @return the requisition
     */
    public Requisition getRequisition() {
        return this.requisition;
    }

    /**
     * Sets the requisition.
     * @param requisition
     *            the new requisition
     */
    public void setRequisition(final Requisition requisition) {
        this.requisition = requisition;
    }

    /**
     * Checks if is edits the.
     * @return true, if is edits the
     */
    public boolean isEdit() {
        return (this.requisition != null && this.requisition.getId() != null);
    }

    /**
     * Checks if is creates the.
     * @return true, if is creates the
     */
    public boolean isCreate() {
        return !isEdit();
    }

    /**
     * Adds the empty requisition item.
     */
    private void addEmptyRequisitionItem() {
        if (isBudget()) {
            final Product product = new Product();
            final RequisitionItem requisitionItem = new RequisitionItem();
            requisitionItem.setQty(Integer.valueOf(1));

            requisitionItem.setProduct(product);
            requisitionItem.setRequisition(this.requisition);
            this.requisition.getRequisitionItens().add(0, requisitionItem);
        }
    }

    /**
     * Checks if is budget.
     * @return true, if is budget
     */
    private boolean isBudget() {
        return this.requisition != null
                && RequisitionStatus.BUDGET.equals(this.requisition.getStatus());
    }

    /**
     * Gets the editable requisition item.
     * @return the editable requisition item
     */
    private RequisitionItem getEditableRequisitionItem() {
        if (isBudget()) {
            return this.requisition.getRequisitionItens().get(0);
        }
        return null;
    }

    /**
     * Gets the editable line product.
     * @return the editableLineProduct
     */
    public Product getEditableLineProduct() {
        return this.editableLineProduct;
    }

    /**
     * Sets the editable line product.
     * @param editableLineProduct
     *            the editableLineProduct to set
     */
    public void setEditableLineProduct(final Product editableLineProduct) {
        this.editableLineProduct = editableLineProduct;
    }

    /**
     * Gets the sku.
     * @return the sku
     */
    @SKU
    public String getSku() {
        return this.sku;
    }

    /**
     * Sets the sku.
     * @param sku
     *            the sku to set
     */
    public void setSku(final String sku) {
        this.sku = sku;
    }

}
