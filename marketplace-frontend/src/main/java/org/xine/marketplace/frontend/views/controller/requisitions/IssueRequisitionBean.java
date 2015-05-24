package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.requisitions.CheckoutRequisitionService;
import org.xine.marketplace.frontend.views.controller.requisitions.events.RequisitionChangedEvent;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Requisition;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class IssueRequisitionBean.
 */
@Named
@RequestScoped
public class IssueRequisitionBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisition service. */
    @Inject
    private CheckoutRequisitionService checkoutRequisitionService;

    /** The requisition changed event. */
    @Inject
    private Event<RequisitionChangedEvent> requisitionChangedEvent;

    /**
     * The requisition.
     * the requisition
     */
    @Inject
    @RequisitionEditer
    private Requisition requisition;

    /**
     * Issue.
     */
    public void issue() {
        if (this.requisition != null && this.checkoutRequisitionService != null) {
            RequisitionEditionHelper.removeEmptyRequisitionItem(this.requisition);
            try {

                this.requisition = this.checkoutRequisitionService.checkout(this.requisition);

                // now we have notify that the requsition requisition has been updated
                // using CDI Events
                this.requisitionChangedEvent.fire(new RequisitionChangedEvent(this.requisition));

                FacesUtil.addInfoMessage("Requisition Issue with success");

            } finally {
                RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
            }
        }
    }

}
