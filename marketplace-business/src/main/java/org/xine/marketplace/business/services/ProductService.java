package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.daos.CategorysRepository;
import org.xine.marketplace.repository.daos.ProductsRepository;
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
        final List<Product> products = this.repository.search(new ProductFilter(null, product
                .getSku()));

        if (products != null && !products.isEmpty()) {
            throw new BusinessException("Já existe um produto com o SKU informado.");
        }

        return this.repository.save(product);
    }

    /**
     * Search.
     * @return the list
     */
    List<Product> search() {
        return null;
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
