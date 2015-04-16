package org.xine.marketplace.repository.daos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.filters.ProductFilter;

/**
 * The Class Products.
 */
public class ProductsRepository {

    /** The manager. */
    @Inject
    private EntityManager manager;

    /**
     * Sets the manager.
     * @param manager
     *            the new manager
     */
    public void setManager(final EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Gets the Product.
     * @param id
     *            the id
     * @return the product
     */
    public Product get(final Long id) {
        return this.manager.find(Product.class, id);
    }

    /**
     * Save.
     * @param product
     *            the p
     * @return the product
     */
    public Product save(final Product product) {
        return this.manager.merge(product);
    }

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Product> search(final ProductFilter filter) {

        final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        final Root<Product> root = criteriaQuery.from(Product.class);

        @SuppressWarnings({"unchecked", "rawtypes", "unused" })
        final Join<Product, Category> joiner = (Join) root.fetch("category");

        criteriaQuery.select(root);

        final List<Predicate> predicates = new ArrayList<>();

        if (haveName(filter)) {
            final Expression<String> name = builder.parameter(String.class, "NAME");
            predicates.add(builder.equal(root.get("name"), name));
        }

        if (haveSKU(filter)) {
            final Expression<String> sku = builder.parameter(String.class, "SKU");
            predicates.add(builder.equal(root.get("sku"), sku));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        final TypedQuery<Product> query = this.manager.createQuery(criteriaQuery);

        if (haveName(filter)) {
            query.setParameter("NAME", filter.getName().trim());
        }
        if (haveSKU(filter)) {
            query.setParameter("SKU", filter.getSku());
        }

        final List<Product> products = query.getResultList();

        return products;
    }

    /**
     * Have sku.
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getSku()} is diferent of null and is not empty,
     *         false otherwise.
     */
    private static boolean haveSKU(final ProductFilter filter) {
        return filter != null && filter.getSku() != null && !filter.getSku().trim().isEmpty();
    }

    /**
     * Have name.
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getName()} is diferent of null and is not empty,
     *         false otherwise.
     */
    private static boolean haveName(final ProductFilter filter) {
        return filter != null && filter.getName() != null && !filter.getName().trim().isEmpty();
    }

}
