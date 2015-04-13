package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * The Class ProductSaverBean.
 */
@Named
@ViewScoped
public class ProductSaverBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The product. */
    private Product product;

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        this.product = new Product();
    }

    /**
     * Save.
     */
    public void save() {
        System.out.println("save the product Action");
        // TODO::missing implementation
        this.product = new Product();
    }

    /**
     * Gets the product.
     * @return the product
     */
    public Product getProduct() {
        return this.product;
    }

}
