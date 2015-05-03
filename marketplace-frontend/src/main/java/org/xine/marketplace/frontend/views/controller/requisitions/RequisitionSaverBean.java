package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.model.entities.Requisition;

import java.io.Serializable;

import javax.annotation.PostConstruct;

/**
 * The Class RequisitionSaverBean.
 */
public class RequisitionSaverBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisition. */
    private Requisition requisition;

    /**
     * Post construct.
     */
    @PostConstruct
    public void postConstruct() {
        // nothing
    }

    /**
     * Save.
     */
    public void save() {
        // nothing
    }

    public Requisition getRequisition() {
        return this.requisition;
    }

    public void setRequisition(final Requisition requisition) {
        this.requisition = requisition;
    }
}
