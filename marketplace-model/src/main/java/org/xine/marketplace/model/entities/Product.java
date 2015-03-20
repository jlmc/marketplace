package org.xine.marketplace.model.entities;

import java.io.Serializable;



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

    /**
     * Instantiates a new product.
     */
    public Product() {
    }

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

    /**
     * Gets the id.
     * @return the id
     */
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
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