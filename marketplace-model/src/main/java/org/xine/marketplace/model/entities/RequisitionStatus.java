package org.xine.marketplace.model.entities;

/**
 * The Enum OrderStatus.
 */
public enum RequisitionStatus {

    /** The budget. */
    BUDGET("Budget"),

    /** The issued. */
    ISSUED("Issued"),

    /** The cancelled. */
    CANCELLED("Cancelled");

    /** The description. */
    private String description;

    /**
     * Instantiates a new requisition status.
     * @param description
     *            the description
     */
    private RequisitionStatus(final String description) {
        this.description = description;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

}
