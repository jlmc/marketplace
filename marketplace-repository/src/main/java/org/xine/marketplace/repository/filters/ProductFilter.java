package org.xine.marketplace.repository.filters;

import java.io.Serializable;

/**
 * The Class ProductFilter.
 */
public class ProductFilter implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	private String name;

	/** The sku. */
	private String sku;

	/**
	 * Instantiates a new product filter.
	 */
	public ProductFilter() {
	}

	/**
	 * Instantiates a new product filter.
	 *
	 * @param name
	 *            the name
	 * @param sku
	 *            the sku
	 */
	public ProductFilter(String name, String sku) {
		super();
		this.name = name;
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
	public void setName(String name) {
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
	public void setSku(String sku) {
		this.sku = sku;
	}

}
