package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * The Class SearchProductBean.
 */
@Named
@ViewScoped
public class ProductSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The sku. */
    private String sku;

    /** The name. */
    private String name;

    /** The product. */
    private List<Product> products;

    /** The selected product. */
    private Product selectedProduct;

    /**
     * Inits the.
     */
    @PostConstruct
    private void init() {
        // Nothing
        this.products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            this.products.add(new Product("SKU-" + i, "Name " + i));

        }
    }

    /**
     * Search.
     */
    public void search() {
        System.out.println("Search operation");
        System.out.println(this.sku);

    }

    /**
     * Delete.
     */
    @SuppressWarnings("static-method")
    public void delete() {
        System.out.println("delete something");
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
     * Gets the product.
     * @return the product
     */
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * Sets the product.
     * @param products
     *            the new products
     */
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    /**
     * Gets the selected product.
     * @return the selected product
     */
    public Product getSelectedProduct() {
        return this.selectedProduct;
    }

    /**
     * Sets the selected product.
     * @param selectedProduct
     *            the new selected product
     */
    public void setSelectedProduct(final Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

}
