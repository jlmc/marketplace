package org.xine.marketplace.frontend.views.controller.products;

import org.xine.marketplace.business.services.products.ProductService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.filters.ProductFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class SearchProductBean. It is the controller of the Search Products view.
 * That view also provides the functionality to delete products.
 * @author Joao Costa
 */
@Named
@ViewScoped
public class ProductSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The service. */
    @Inject
    private ProductService service;

    /** The filter. */
    private ProductFilter filter;

    /** The product. */
    private List<Product> products;

    /** The selected product. */
    private Product selectedProduct;

    /**
     * Post construct.
     */
    @PostConstruct
    private void postConstruct() {
        // Nothing
        this.filter = new ProductFilter();
        this.products = new ArrayList<>();
    }

    /**
     * Search.
     */
    public void search() {
        this.products = this.service.search(this.filter);
    }

    /**
     * Delete.
     */
    public void delete() {
        System.out.println("delete something");
        this.service.delete(this.selectedProduct);

        // if sucess remove product from the colection.
        this.products.remove(this.selectedProduct);

        FacesUtil.addInfoMessage("Product " + this.selectedProduct.getSku() + " was been removed.");

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

    /**
     * Gets the filter.
     * @return the filter
     */
    public ProductFilter getFilter() {
        return this.filter;
    }

    /**
     * Sets the filter.
     * @param filter
     *            the new filter
     */
    public void setFilter(final ProductFilter filter) {
        this.filter = filter;
    }

}
