package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Class Product.
 */
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

	/**
	 * Instantiates a new product.
	 */
	public Product() {
	}

	/**
	 * Instantiates a new product.
	 * 
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
	 * 
	 * @return the sku
	 */
	public String getSku() {
		return this.sku;
	}

	/**
	 * Sets the sku.
	 * 
	 * @param sku
	 *            the new sku
	 */
	public void setSku(final String sku) {
		this.sku = sku;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
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
	 * Gets the unit value.
	 *
	 * @return the unit value
	 */
	public BigDecimal getUnitValue() {
		return unitValue;
	}

	/**
	 * Sets the unit value.
	 *
	 * @param unitValue
	 *            the new unit value
	 */
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	/**
	 * Gets the stock qty.
	 *
	 * @return the stock qty
	 */
	public Integer getStockQty() {
		return stockQty;
	}

	/**
	 * Sets the stock qty.
	 *
	 * @param stockQty
	 *            the new stock qty
	 */
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category
	 *            the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
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