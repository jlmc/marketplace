package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.daos.CategorysRepository;
import org.xine.marketplace.repository.daos.ProductsRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.filters.ProductFilter;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Interface ProductService.
 */
@Default
public class ProductService implements Serializable {

	/** The repository. */
	@Inject
	private ProductsRepository repository;

	/** The categorys repository. */
	@Inject
	private CategorysRepository categorysRepository;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Save.
	 * @param product
	 *            the product
	 * @return the product
	 * @throws BusinessException
	 *             the business exception
	 */
	@Transactional
	public Product save(final Product product) throws BusinessException {

		Product alreadyExistingProduct = this.repository.getBySKU(product.getSku());
		/**
		 * BUSINESS RULE 1:: if the exists already a product with the same SKU in the system, 
		 * and it is not the same product ( with the same id )
		 * then can't save or update the product 
		 */
		if (alreadyExistingProduct != null &&  !alreadyExistingProduct.equals(product)) {
			throw new BusinessException("Já existe um produto com o SKU " + product.getSku() + " .");
		}

		return this.repository.save(product);
	}

	@Transactional
	private void delete(Product product){
		try{
			this.repository.remove(product);
		}catch(RepositoryException e){
			throw new BusinessException("O produto não pode ser removido, pois esta esta a ser utilizado.");
		}
	}

	/**
	 * Search.
	 * @return the list
	 */
	public List<Product> search(final ProductFilter filter) {
		return this.repository.search(filter);
	}

	/**
	 * Gets the root categorys.
	 * @return the root categorys
	 */
	public List<Category> getRootCategorys() {
		return this.categorysRepository.getRootCategorys();
	}

	/**
	 * Gets the childs categories.
	 * @param father
	 *            the father
	 * @return the childs categories
	 */
	public List<Category> getChildsCategories(final Category father) {
		return this.categorysRepository.getChildCategorys(father);
	}

}
