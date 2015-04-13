package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * The Class Order.
 */
@Entity
@Table(name = "requisition")
public class Requisition implements Serializable {

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

    /** The rebate value. */
    private BigDecimal rebateValue;
    
    /** The total value. */
    private BigDecimal totalValue;
    
    private RequisitionStatus status;
    
    private PaymentMethod paymentMethod;
    
    
    
    /**
     * Instantiates a new requisition.
     */
    public  Requisition() {
	}
   
    /**
     * Instantiates a new requisition.
     *
     * @param id the id
     */
    public Requisition(final Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name="creation_Date")
    @Temporal(TemporalType.TIMESTAMP)
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
    @Column(columnDefinition = "text")
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
    @NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "delivery_date", nullable = false)
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

    
    /**
     * Gets the rebate value.
     *
     * @return the rebate value
     */
    @NotNull
	@Column(name = "rebate_value", nullable = false, precision = 10, scale = 2)
	public BigDecimal getRebateValue() {
		return rebateValue;
	}

	/**
	 * Sets the rebate value.
	 *
	 * @param rebateValue the new rebate value
	 */
	public void setRebateValue(BigDecimal rebateValue) {
		this.rebateValue = rebateValue;
	}

	/**
	 * Gets the total value.
	 *
	 * @return the total value
	 */
	@NotNull
	@Column(name = "total_value", nullable = false, precision = 10, scale = 2)
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	/**
	 * Sets the total value.
	 *
	 * @param totalValue the new total value
	 */
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public RequisitionStatus getStatus() {
		return status;
	}

	public void setStatus(RequisitionStatus status) {
		this.status = status;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Payment_Method", nullable = false, length = 20)
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
