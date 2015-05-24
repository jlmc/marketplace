package org.xine.marketplace.frontend.views.controller.requisitions;

import org.primefaces.event.SelectEvent;
import org.xine.marketplace.business.services.requisitions.RequisitionService;
import org.xine.marketplace.frontend.views.controller.requisitions.events.RequisitionChangedEvent;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.PaymentMethod;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.util.Strings;
import org.xine.marketplace.validator.constraints.NotBlank;
import org.xine.marketplace.validator.constraints.SKU;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class RequisitionSaverBean. It is a Controller of the creation and edit Requisitions.
 * @author Joao Costa
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
    @Produces
    @RequisitionEditer
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
                RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
            }

            calcTotals();
        }
    }

    /**
     * Clean.
     */
    private void clean() {
        this.requisition = new Requisition();
        RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
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
     * Update qty.
     * @param item
     *            the item
     * @param lineIndex
     *            the line index
     */
    @SuppressWarnings("boxing")
    public void updateQty(final RequisitionItem item, final int lineIndex) {
        if (item.getQty() < 1) {
            if (lineIndex == 0) {
                item.setQty(1);
            } else {
                // this.requisition.getRequisitionItens().remove(item);
                this.requisition.getRequisitionItens().remove(lineIndex);
            }
        }

        this.requisition = this.requisitionService.calcTotals(this.requisition);
    }

    /**
     * Save.
     */
    public void save() {
        // we can't save the firts item
        RequisitionEditionHelper.removeEmptyRequisitionItem(this.requisition);

        try {
            this.requisition = this.requisitionService.save(this.requisition);

            FacesUtil.addInfoMessage("The requisition has been saved successfully!");
        } finally {
            // after save the requisition we have to add the empty line that
            RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
        }
    }

    // public List<Client> completeClient(final String clientName) {
    // return this.requisitionService.searchClientByName(clientName);
    // }

    /**
     * Handler Client selected on Dialod Box returns.
     * @param event
     *            the event
     */
    public void clientSelected(final SelectEvent event) {
        final Client selectedClient = (Client) event.getObject();
        this.requisition.setClient(selectedClient);
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
        final RequisitionItem item = RequisitionEditionHelper
                .getEditableRequisitionItem(this.requisition);
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

                RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
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

    /**
     * Requisition changed event handler.
     * @param event
     *            the event
     */
    public void requisitionChangedEventHandler(@Observes final RequisitionChangedEvent event) {
        this.requisition = event.getRequisition();
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

    /**
     * Gets the client name.
     * @return the client name
     */
    @NotBlank
    public String getClientName() {
        String clientName = null;
        if (this.requisition != null && this.requisition.getClient() != null) {
            clientName = this.requisition.getClient().getName();
        }
        return clientName;
    }

    /**
     * Sets the client name.
     * @param clientName
     *            the new client name
     */
    public void setClientName(final String clientName) {
        // don't need to do any thing, is just view have a getClientName Invokation
    }

}
