package org.xine.marketplace.frontend.views.controller.requisitions.events;

import org.xine.marketplace.model.entities.Requisition;

/**
 * The Class RequisitionChangedEvent.
 */
public class RequisitionChangedEvent {

    /** The requisition. */
    private final Requisition requisition;

    /**
     * Instantiates a new requisition changed event.
     * @param requisition
     *            the requisition
     */
    public RequisitionChangedEvent(final Requisition requisition) {
        super();
        this.requisition = requisition;
    }

    /**
     * Gets the requisition.
     * @return the requisition
     */
    public Requisition getRequisition() {
        return this.requisition;
    }

}
