package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Order.
 */
public class Order implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The creation date. */
    private Date creationDate;

    /** The notes. */
    private String notes;

    /** The delivery date. */
    private Date deliveryDate;

    /** The seller. */
    private User seller;

    /** The client. */
    private Client client;

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

    /**
     * Gets the creation date.
     * @return the creation date
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Sets the creation date.
     * @param creationDate
     *            the new creation date
     */
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the notes.
     * @return the notes
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * Sets the notes.
     * @param notes
     *            the new notes
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    /**
     * Gets the delivery date.
     * @return the delivery date
     */
    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    /**
     * Sets the delivery date.
     * @param deliveryDate
     *            the new delivery date
     */
    public void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Gets the seller.
     * @return the seller
     */
    public User getSeller() {
        return this.seller;
    }

    /**
     * Sets the seller.
     * @param seller
     *            the new seller
     */
    public void setSeller(final User seller) {
        this.seller = seller;
    }

    /**
     * Gets the client.
     * @return the client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Sets the client.
     * @param client
     *            the new client
     */
    public void setClient(final Client client) {
        this.client = client;
    }

}
