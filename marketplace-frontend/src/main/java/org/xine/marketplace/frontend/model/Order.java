package org.xine.marketplace.frontend.model;

import java.io.Serializable;

/**
 * The Class Order.
 */
public class Order implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /**
     * Instantiates a new order.
     */
    public Order() {
    }

    /**
     * Instantiates a new order.
     * @param id
     *            the id
     */
    public Order(final Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
