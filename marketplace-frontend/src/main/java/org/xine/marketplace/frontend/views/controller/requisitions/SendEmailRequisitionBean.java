package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.requisitions.SendRequisitionEmailService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Requisition;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class SendEmailRequisitionBean.
 */
@Named
@RequestScoped
public class SendEmailRequisitionBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Inject
    @RequisitionEditer
    private Requisition requisition;

    @Inject
    private SendRequisitionEmailService emailService;

    /**
     * Send email.
     */
    public void sendEmail() {
        this.emailService.send(this.requisition);
        FacesUtil
                .addInfoMessage("An email was sent with the data of Requisition were sent to the client successfully");
    }
}
