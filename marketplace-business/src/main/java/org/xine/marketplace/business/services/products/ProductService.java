package org.xine.marketplace.business.services.products;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.filters.ProductFilter;
import org.xine.marketplace.repository.daos.CategorysRepository;
import org.xine.marketplace.repository.daos.ProductsRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.util.Transactional;

/**
 * The Interface ProductService.
 */
@Default
public class ProductService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The products repository. */
    @Inject
    private ProductsRepository productsRepository;

    /** The categorys repository. */
    @Inject
    private CategorysRepository categorysRepository;

    /**
     * Save.
     *
     * @param product
     *            the product
     * @return the product
     * @throws BusinessException
     *             the business exception
     */
    @Transactional
    public Product save(final Product product) throws BusinessException {

	final Product alreadyExistingProduct = this.productsRepository
		.getBySKU(product.getSku());
	/**
	 * BUSINESS RULE 1:: if the exists already a product with the same SKU
	 * in the system, and it is not the same product ( with the same id )
	 * then can't save or update the product
	 */
	if (alreadyExistingProduct != null
		&& !alreadyExistingProduct.equals(product)) {
	    throw new BusinessException("Já existe um produto com o SKU "
		    + product.getSku() + " .");
	}

	return this.productsRepository.save(product);
    }

    /**
     * Delete.
     *
     * @param product
     *            the product
     */
    @Transactional
    public void delete(final Product product) {
	try {
	    this.productsRepository.remove(product);
	} catch (final RepositoryException e) {
	    throw new BusinessException(
		    "O produto não pode ser removido, pois esta esta a ser utilizado.");
	}
    }

    /**
     * Search.
     *
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Product> search(final ProductFilter filter) {
	return this.productsRepository.search(filter);
    }

    /**
     * Gets the root categorys.
     *
     * @return the root categorys
     */
    public List<Category> getRootCategorys() {
	return this.categorysRepository.getRootCategorys();
    }

    /**
     * Gets the childs categories.
     *
     * @param father
     *            the father
     * @return the childs categories
     */
    public List<Category> getChildsCategories(final Category father) {
	return this.categorysRepository.getChildCategorys(father);
    }

    /**
     * Gets the products repository.
     *
     * @return the products repository
     */
    protected ProductsRepository getProductsRepository() {
	return this.productsRepository;
    }

    /**
     * Sets the products repository.
     *
     * @param productsRepository
     *            the new products repository
     */
    protected void setProductsRepository(
	    final ProductsRepository productsRepository) {
	this.productsRepository = productsRepository;
    }

    /**
     * Gets the categorys repository.
     *
     * @return the categorys repository
     */
    protected CategorysRepository getCategorysRepository() {
	return this.categorysRepository;
    }

    /**
     * Sets the categorys repository.
     *
     * @param categorysRepository
     *            the new categorys repository
     */
    protected void setCategorysRepository(
	    final CategorysRepository categorysRepository) {
	this.categorysRepository = categorysRepository;
    }

}
