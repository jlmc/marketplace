package org.xine.marketplace.model.filters;

import java.io.Serializable;

import org.xine.marketplace.validator.constraints.SKU;

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
    public ProductFilter(final String name, final String sku) {
	super();
	this.name = name;
	setSku(sku);
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
     * Gets the sku.
     * 
     * @return the sku
     */
    @SKU
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
	this.sku = sku == null ? null : sku.toUpperCase();
    }

}
