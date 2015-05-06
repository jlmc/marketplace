package org.xine.marketplace.frontend.views.controller.requisitions;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.marketplace.business.services.RequisitionService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Requisition;

/**
 * The Class IssueRequisitionBean.
 */
@Named
@ViewScoped
public class IssueRequisitionBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisition service. */
    @Inject
    private RequisitionService requisitionService;

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
        System.out.println("TODO issue requisition");
        if (this.requisition != null && this.requisitionService != null) {
            RequisitionEditionHelper.removeEmptyRequisitionItem(this.requisition);
            try {

                this.requisition = this.requisitionService.issue(this.requisition);

                FacesUtil.addInfoMessage("Requisition Issue with success");

            } finally {
                RequisitionEditionHelper.addEmptyRequisitionItem(this.requisition);
            }
        }
    }

}
