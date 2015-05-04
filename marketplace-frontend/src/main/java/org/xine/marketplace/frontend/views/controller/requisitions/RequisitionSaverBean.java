package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.RequisitionService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.PaymentMethod;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.User;

import java.io.Serializable;
import java.util.List;

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
        }
    }

    /**
     * Clean.
     */
    private void clean() {
        this.requisition = new Requisition();
    }

    // -------------------------------------------------------------------------
    //
    // Handlers
    //
    // -------------------------------------------------------------------------

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
}
