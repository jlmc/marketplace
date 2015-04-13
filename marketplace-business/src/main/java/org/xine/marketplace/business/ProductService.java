package org.xine.marketplace.business;

import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;

/**
 * The Class ProductService.
 */
public class ProductService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Save.
     * @param product
     *            the product
     */
    public void save(final Product product) {
        System.out.println("ProductService will no save noting eat...");

    }

}
