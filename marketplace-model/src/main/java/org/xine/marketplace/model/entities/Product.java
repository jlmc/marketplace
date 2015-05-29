package org.xine.marketplace.model.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.xine.marketplace.validator.constraints.SKU;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class Product.
 */
@Entity
@Table(name = "Product")
public class Product implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The sku. */
    private String sku;

    /** The name. */
    private String name;

    /** The unit value. */
    private BigDecimal unitValue;

    /** The stock qty. */
    private Integer stockQty;

    /** The category. */
    private Category category;

    /** The requisition itens. */
    private Set<RequisitionItem> requisitionItens = new HashSet<>();

    /**
     * Instantiates a new product.
     */
    public Product() {}

    /**
     * Instantiates a new product.
     * @param sku
     *            the sku
     * @param name
     *            the name
     */
    public Product(final String sku, final String name) {
        super();
        this.sku = sku;
        this.name = name;
    }

    /**
     * Gets the sku.
     * @return the sku
     */
    // @Pattern(regexp="([a-zA-Z]{2}\\d{4,18})?")
    @NotNull
    @SKU
    @Column(nullable = false, length = 20, unique = true)
    public String getSku() {
        return this.sku;
    }

    /**
     * Sets the sku.
     * @param sku
     *            the new sku
     */
    public void setSku(final String sku) {
        this.sku = sku;
    }

    /**
     * Gets the name.
     * @return the name
     */
    @Size(max = 80)
    @NotBlank
    @Column(nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
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
     * Gets the unit value.
     * @return the unit value
     */
    @NotNull
    @Min(value = 1)
    @Column(name = "Unit_Value", nullable = false, precision = 10, scale = 2)
    public BigDecimal getUnitValue() {
        return this.unitValue;
    }

    /**
     * Sets the unit value.
     * @param unitValue
     *            the new unit value
     */
    public void setUnitValue(final BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * Gets the stock qty.
     * @return the stock qty
     */
    @NotNull
    @Max(9999)
    @Min(0)
    @Column(name = "Stock_Qty", nullable = false, length = 6)
    public Integer getStockQty() {
        return this.stockQty;
    }

    /**
     * Sets the stock qty.
     * @param stockQty
     *            the new stock qty
     */
    public void setStockQty(final Integer stockQty) {
        this.stockQty = stockQty;
    }

    /**
     * Gets the category.
     * @return the category
     */
    @NotNull
    // @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    /**
     * Sets the category.
     * @param category
     *            the new category
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * Gets the requisition itens.
     * @return the requisitionItens
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public Set<RequisitionItem> getRequisitionItens() {
        return this.requisitionItens;
    }

    /**
     * Sets the requisition itens.
     * @param requisitionItens
     *            the requisitionItens to set
     */
    public void setRequisitionItens(final Set<RequisitionItem> requisitionItens) {
        this.requisitionItens = requisitionItens;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
