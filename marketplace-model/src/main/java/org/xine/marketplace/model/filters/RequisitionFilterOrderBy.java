package org.xine.marketplace.model.filters;

import java.io.Serializable;

/**
 * The Class RequisitionFilterOrderBy, definition of the order by to use on the requisition filter
 * operation.
 * @author Joao Costa
 */
public class RequisitionFilterOrderBy implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The Enum RequisitionFilterOrderByType.
     */
    public enum RequisitionFilterOrderByType {
        /** The id. */
        ID,
        /** The client name. */
        CLIENT_NAME,
        /** The seller name. */
        SELLER_NAME,
        /** The creation date. */
        CREATION_DATE,
        /** The value. */
        VALUE;
    }

    /** The order by type. */
    private RequisitionFilterOrderByType orderByType;

    /** The ascendant. */
    private boolean descendent;

    /**
     * Instantiates a new requisition filter order by.
     * @param type
     *            the order by type
     * @param descendent
     *            the ascendant
     */
    public RequisitionFilterOrderBy(final RequisitionFilterOrderByType type,
            final boolean descendent) {
        super();
        this.orderByType = type;
        this.descendent = descendent;
    }

    /**
     * Instantiates a new requisition filter order by.
     */
    public RequisitionFilterOrderBy() {
        super();
        this.orderByType = RequisitionFilterOrderByType.ID;
    }

    /**
     * Gets the order by type.
     * @return the order by type
     */
    public RequisitionFilterOrderByType getOrderByType() {
        return this.orderByType;
    }

    /**
     * Sets the order by type.
     * @param orderByType
     *            the new order by type
     */
    public void setOrderByType(final RequisitionFilterOrderByType orderByType) {
        this.orderByType = orderByType;
    }

    /**
     * Checks if is ascendant.
     * @return true, if is ascendant
     */
    public boolean isDescendent() {
        return this.descendent;
    }

    /**
     * Sets the ascendant.
     * @param descendent
     *            the new descendent
     */
    public void setDescendent(final boolean descendent) {
        this.descendent = descendent;
    }

}
