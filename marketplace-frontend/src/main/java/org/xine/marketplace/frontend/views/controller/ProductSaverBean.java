package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.business.services.ProductService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
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
    	this.rootCategorys = service.getRootCategorys();
        this.product = new Product();
    }

    
    /**
     * Inits the.
     */
    public void init(){
    	//nothing
    }
    
    /**
     * Save.
     */
    public void save() {
        System.out.println("save the product Action");

        this.service.save(this.product);
        
        FacesUtil.addErrorMessage("Produto savo com sucesso");

        
        this.product = new Product();
    }

    /**
     * Load child categorys.
     */
    public void loadChildCategorys(){
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
	 *
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * Gets the category master.
	 *
	 * @return the category master
	 */
	public Category getCategoryMaster() {
		return categoryMaster;
	}

	/**
	 * Sets the category master.
	 *
	 * @param categoryMaster the new category master
	 */
	public void setCategoryMaster(Category categoryMaster) {
		this.categoryMaster = categoryMaster;
	}


	/**
	 * Gets the root categorys.
	 *
	 * @return the root categorys
	 */
	public List<Category> getRootCategorys() {
		return rootCategorys;
	}


	


	



}
