package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.requisitions.CancelRequisitionService;
import org.xine.marketplace.frontend.views.controller.requisitions.events.RequisitionChangedEvent;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Requisition;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class RequisitionCancelBean.
 */

// this bean don't need to be ViewScoped, it can be just RequestScoped
// because we don't need to keep the state of the Bean
// @javax.faces.bean.ViewScoped

@Named
@RequestScoped
public class RequisitionCancelBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The cancel requisition service. */
    @Inject
    private CancelRequisitionService cancelRequisitionService;

    /** The requisition changed event. */
    @Inject
    private Event<RequisitionChangedEvent> requisitionChangedEvent;

    /** The requisition. */
    @Inject
    @RequisitionEditer
    private Requisition requisition;

    /**
     * Cancel.
     */
    public void cancel() {
        boolean done = false;

        RequisitionEditionHelper.removeEmptyRequisitionItem(this.requisition);
        try {

            this.requisition = this.cancelRequisitionService.cancel(this.requisition);
            done = true;

            FacesUtil.addInfoMessage("Successfully canceled Requisition");
        } finally {
            RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
        }
        if (done) {
            this.requisitionChangedEvent.fire(new RequisitionChangedEvent(this.requisition));
        }

    }

}
