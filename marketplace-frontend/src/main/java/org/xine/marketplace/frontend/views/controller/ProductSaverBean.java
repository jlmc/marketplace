package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.business.services.ProductService;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
    
    private List<Category> categories;
    
    

    /** The service. */
    @Default
    @Inject
    private ProductService service;

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

        this.service.save(this.product);

        this.product = new Product();
    }

    /**
     * Gets the product.
     * @return the product
     */
    public Product getProduct() {
        return this.product;
    }

	public List<Category> getCategories() {
		return categories;
	}



}
