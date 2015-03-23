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
	 * 
	 * @param id
	 *            the id
	 */
	public Order(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes
	 *            the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Gets the delivery date.
	 *
	 * @return the delivery date
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Sets the delivery date.
	 *
	 * @param deliveryDate
	 *            the new delivery date
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * Gets the seller.
	 *
	 * @return the seller
	 */
	public User getSeller() {
		return seller;
	}

	/**
	 * Sets the seller.
	 *
	 * @param seller
	 *            the new seller
	 */
	public void setSeller(User seller) {
		this.seller = seller;
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Sets the client.
	 *
	 * @param client
	 *            the new client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

}
