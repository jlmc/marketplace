package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.business.services.ProductService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
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

    /** The category master. */
    private Category categoryMaster;

    /** The root categorys. */
    private List<Category> rootCategorys;

    /** The categories. */
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
        clean();
        this.rootCategorys = this.service.getRootCategorys();

    }

    /**
     * Inits the.
     */
    @SuppressWarnings("static-method")
    public void init() {
        // if (FacesUtil.isNotPostback()) {
        // // do the operation here
        // }
    }

    /**
     * Save.
     */
    public void save() {

        this.product = this.service.save(this.product);

        FacesUtil.addInfoMessage("Produto savo com sucesso");

        clean();
    }

    private void clean() {
        this.product = new Product();
        this.categoryMaster = null;
        this.categories = new ArrayList<>();
    }

    /**
     * Load child categorys.
     */
    public void loadChildCategorys() {
        this.categories = this.service.getChildsCategories(this.categoryMaster);
    }

    /**
     * Gets the product.
     * @return the product
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Gets the categories.
     * @return the categories
     */
    public List<Category> getCategories() {
        return this.categories;
    }

    /**
     * Gets the category master.
     * @return the category master
     */
    public Category getCategoryMaster() {
        return this.categoryMaster;
    }

    /**
     * Sets the category master.
     * @param categoryMaster
     *            the new category master
     */
    public void setCategoryMaster(final Category categoryMaster) {
        this.categoryMaster = categoryMaster;
    }

    /**
     * Gets the root categorys.
     * @return the root categorys
     */
    public List<Category> getRootCategorys() {
        return this.rootCategorys;
    }

}
