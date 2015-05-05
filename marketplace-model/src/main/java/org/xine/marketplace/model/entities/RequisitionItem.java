/**
 *
 */
package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class RequisitionItem.
 * @author xine
 */
@Entity
@Table(name = "requisition_item")
public class RequisitionItem implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The qty. */
    private Integer qty = Integer.valueOf(0);

    /** The unit value. */
    private BigDecimal unitValue = BigDecimal.ZERO;

    /** The product. */
    private Product product;

    /** The requisition. */
    private Requisition requisition;

    /**
     * Instantiates a new requisition item.
     */
    public RequisitionItem() {}

    /**
     * Gets the requisition.
     * @return the requisition
     */
    @ManyToOne
    @JoinColumn(name = "requisition_id", nullable = false)
    public Requisition getRequisition() {
        return this.requisition;
    }

    /**
     * Gets the totalvalue.
     * @return the totalvalue
     */
    @SuppressWarnings("boxing")
    @Transient
    public BigDecimal getTotalvalue() {
        return getUnitValue().multiply(new BigDecimal(getQty()));
    }

    /**
     * Sets the requisition.
     * @param requisition
     *            the requisition to set
     */
    public void setRequisition(final Requisition requisition) {
        this.requisition = requisition;
    }

    /**
     * Gets the product.
     * @return the product
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    public Product getProduct() {
        return this.product;
    }

    /**
     * Sets the product.
     * @param product
     *            the product to set
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    /**
     * Gets the unit value.
     * @return the unitValue
     */
    @Column(name = "unit_value", nullable = false, precision = 10, scale = 2)
    public BigDecimal getUnitValue() {
        return this.unitValue;
    }

    /**
     * Sets the unit value.
     * @param unitValue
     *            the unitValue to set
     */
    public void setUnitValue(final BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * Gets the qty.
     * @return the qty
     */
    @Column(name = "qty", nullable = false, length = 4)
    public Integer getQty() {
        return this.qty;
    }

    /**
     * Sets the qty.
     * @param qty
     *            the qty to set
     */
    public void setQty(final Integer qty) {
        this.qty = qty;
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
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
