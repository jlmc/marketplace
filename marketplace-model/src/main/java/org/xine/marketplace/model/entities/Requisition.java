package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    /** The freight value. */
    private BigDecimal shippingValue;

    /** The status. */
    private RequisitionStatus status;

    /** The payment method. */
    private PaymentMethod paymentMethod;

    /** The delivery address. */
    private DeliveryAddress deliveryAddress;

    /** The requisition itens. */
    private List<RequisitionItem> requisitionItens = new ArrayList<>();

    /**
     * Instantiates a new requisition.
     */
    public Requisition() {
        super();
        this.deliveryAddress = new DeliveryAddress();
        this.status = RequisitionStatus.BUDGET;
        this.rebateValue = BigDecimal.ZERO;
        this.totalValue = BigDecimal.ZERO;
        this.shippingValue = BigDecimal.ZERO;
        // this.paymentMethod = PaymentMethod.CREDIT_CARD;

    }

    /**
     * Instantiates a new requisition.
     * @param id
     *            the id
     * @param creationDate
     *            the creation date
     * @param notes
     *            the notes
     * @param deliveryDate
     *            the delivery date
     * @param seller
     *            the seller
     * @param client
     *            the client
     * @param rebateValue
     *            the rebate value
     * @param totalValue
     *            the total value
     * @param status
     *            the status
     * @param paymentMethod
     *            the payment method
     * @param deliveryAddress
     *            the delivery address
     * @param requisitionItens
     *            the requisition itens
     */
    public Requisition(final Long id, final Date creationDate, final String notes,
            final Date deliveryDate, final User seller, final Client client,
            final BigDecimal rebateValue, final BigDecimal totalValue,
            final RequisitionStatus status, final PaymentMethod paymentMethod,
            final DeliveryAddress deliveryAddress, final List<RequisitionItem> requisitionItens) {
        this();
        this.id = id;
        this.creationDate = creationDate;
        this.notes = notes;
        this.deliveryDate = deliveryDate;
        this.seller = seller;
        this.client = client;
        this.rebateValue = rebateValue;
        this.totalValue = totalValue;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.requisitionItens = requisitionItens;
    }

    /**
     * Gets the sub total value.
     * @return the sub total value
     */
    @Transient
    public BigDecimal getSubTotalValue() {
        return getTotalValue().subtract(this.shippingValue).add(getRebateValue());
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
    @Size(max = 1024)
    // @Column(columnDefinition = "text")
    @Column(name = "notes", length = 1024)
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
     * Gets the shipping value.
     * @return the shipping value
     */
    @NotNull
    @Column(name = "shipping_value", nullable = false, precision = 10, scale = 2)
    public BigDecimal getShippingValue() {
        return this.shippingValue;
    }

    /**
     * Sets the shipping value.
     * @param shippingValue
     *            the new shipping value
     */
    public void setShippingValue(final BigDecimal shippingValue) {
        this.shippingValue = shippingValue;
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
     * Gets the requisition itens.
     * @return the requisitionItens
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requisition")
    public List<RequisitionItem> getRequisitionItens() {
        return this.requisitionItens;
    }

    /**
     * Sets the requisition itens.
     * @param requisitionItens
     *            the requisitionItens to set
     */
    public void setRequisitionItens(final List<RequisitionItem> requisitionItens) {
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

    /**
     * Checks if the status is BUDGET .
     * @return true, if is issued
     */
    @Transient
    public boolean isBudget() {
        return RequisitionStatus.BUDGET.equals(getStatus());
    }

    /**
     * Checks if the status is CANCELLED .
     * @return true, if is cancelled
     */
    @Transient
    public boolean isCancelled() {
        return RequisitionStatus.CANCELLED.equals(getStatus());
    }

    /**
     * Checks if the status is ISSUED .
     * @return true, if is issued
     */
    @Transient
    public boolean isIssued() {
        return RequisitionStatus.ISSUED.equals(getStatus());
    }

    /**
     * Checks if is issueble.
     * @return true, if is issueble
     */
    @Transient
    public boolean isIssueble() {
        return isExistent() && isBudget();
    }

    /**
     * Checks if is existent.
     * @return true, if is existent
     */
    @Transient
    public boolean isExistent() {
        return getId() != null;
    }

    /**
     * Checks if is not issueble.
     * @return true, if is not issueble
     */
    @Transient
    public boolean isNotIssueble() {
        return !isIssueble();
    }

    /**
     * Checks if is cancelable.
     * @return true, if is cancelable
     */
    @Transient
    public boolean isCancelable() {
        return isExistent() && !isCancelled();
    }

    /**
     * Checks if is not cancelable.
     * @return true, if is not cancelable
     */
    @Transient
    public boolean isNotCancelable() {
        return !isCancelable();
    }

    /**
     * Checks if is not editable.
     * @return true, if is not editable
     */
    @Transient
    public boolean isNotEditable() {
        return !isEditable();
    }

    /**
     * Checks if is editable.
     * @return true, if is editable
     */
    @Transient
    public boolean isEditable() {
        return isBudget();
    }

    /**
     * Checks if is mailable.
     * @return true, if is mailable
     */
    @Transient
    public boolean isMailable() {
        return isExistent() && !isCancelled();
    }

    /**
     * Checks if is not mailable.
     * @return true, if is not mailable
     */
    @Transient
    public boolean isNotMailable() {
        return !isMailable();
    }
}
