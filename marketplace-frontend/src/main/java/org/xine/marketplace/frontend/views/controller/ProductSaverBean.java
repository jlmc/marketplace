package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductSaverBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Product product;

    @PostConstruct
    public void init() {
        this.product = new Product();
    }

    // @Inject
    // private ProductService productService;

    public void save() {
        System.out.println("save the product Action");

        // if (this.productService != null) {
        // this.productService.save(this.product);
        // }

        this.product = new Product();
    }

    public Product getProduct() {
        return this.product;
    }

}
