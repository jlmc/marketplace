package org.xine.marketplace.frontend.model;

import java.io.Serializable;

/**
 * The Class Product.
 */
public class Product implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The sku. */
    private String sku;

    /** The name. */
    private String name;

    /**
     * Instantiates a new product.
     */
    public Product() {
    }

    public Product(final String sku, final String name) {
        super();
        this.sku = sku;
        this.name = name;
    }

    /**
     * Gets the sku.
     * @return the sku
     */
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

}
