package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    /** The status. */
    private RequisitionStatus status;

    /** The payment method. */
    private PaymentMethod paymentMethod;

    /** The delivery address. */
    private DeliveryAddress deliveryAddress;

    private Set<RequisitionItem> requisitionItens = new HashSet<>();

    /**
     * Instantiates a new requisition.
     */
    public Requisition() {}

    /**
     * Instantiates a new requisition.
     * @param id
     *            the id
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
    @Column(name = "creation_Date")
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
    // @Column(columnDefinition = "text")
    @Column(name = "notes", length = 2048)
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
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    // by default is fetch e EAGER @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
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
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    // by default is fetch e EAGER @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
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
     * @return the rebate value
     */
    @NotNull
    @Column(name = "rebate_value", nullable = false, precision = 10, scale = 2)
    public BigDecimal getRebateValue() {
        return this.rebateValue;
    }

    /**
     * Sets the rebate value.
     * @param rebateValue
     *            the new rebate value
     */
    public void setRebateValue(final BigDecimal rebateValue) {
        this.rebateValue = rebateValue;
    }

    /**
     * Gets the total value.
     * @return the total value
     */
    @NotNull
    @Column(name = "total_value", nullable = false, precision = 10, scale = 2)
    public BigDecimal getTotalValue() {
        return this.totalValue;
    }

    /**
     * Sets the total value.
     * @param totalValue
     *            the new total value
     */
    public void setTotalValue(final BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * Gets the status.
     * @return the status
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    public RequisitionStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     * @param status
     *            the new status
     */
    public void setStatus(final RequisitionStatus status) {
        this.status = status;
    }

    /**
     * Gets the payment method.
     * @return the payment method
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Sets the payment method.
     * @param paymentMethod
     *            the new payment method
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the delivery address.
     * @return the delivery address
     */
    @Embedded
    public DeliveryAddress getDeliveryAddress() {
        return this.deliveryAddress;
    }

    /**
     * Sets the delivery address.
     * @param deliveryAddress
     *            the new delivery address
     */
    public void setDeliveryAddress(final DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * @return the requisitionItens
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requisition")
    public Set<RequisitionItem> getRequisitionItens() {
        return this.requisitionItens;
    }

    /**
     * @param requisitionItens
     *            the requisitionItens to set
     */
    public void setRequisitionItens(final Set<RequisitionItem> requisitionItens) {
        this.requisitionItens = requisitionItens;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Requisition [id=" + this.id + ", creationDate=" + this.creationDate + ", notes="
                + this.notes + ", deliveryDate=" + this.deliveryDate + ", seller=" + this.seller
                + ", client=" + this.client + ", rebateValue=" + this.rebateValue + ", totalValue="
                + this.totalValue + ", status=" + this.status + ", paymentMethod="
                + this.paymentMethod + ", deliveryAddress=" + this.deliveryAddress
                + ", requisitionItens=" + this.requisitionItens + "]";
    }

}
