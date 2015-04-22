package org.xine.marketplace.repository.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.filters.ProductFilter;
import org.xine.marketplace.repository.exceptions.RepositoryException;

/**
 * The Class Products.
 */
public class ProductsRepository implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The manager. */
    @Inject
    private EntityManager manager;

    /**
     * Sets the manager.
     *
     * @param manager
     *            the new manager
     */
    public void setManager(final EntityManager manager) {
	this.manager = manager;
    }

    /**
     * Gets the Product.
     *
     * @param id
     *            the id
     * @return the product
     */
    public Product get(final Long id) {
	return this.manager.find(Product.class, id);
    }

    /**
     * Save.
     *
     * @param product
     *            the p
     * @return the product
     */
    public Product save(final Product product) {
	return this.manager.merge(product);
    }

    /**
     * Search.
     *
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Product> search(final ProductFilter filter) {

	final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
	final CriteriaQuery<Product> criteriaQuery = builder
		.createQuery(Product.class);
	final Root<Product> root = criteriaQuery.from(Product.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	final Join<Product, Category> joiner = (Join) root.fetch("category");

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	final Join<Category, Category> catJoin = (Join) joiner
		.fetch("masterCategory");

	criteriaQuery.select(root);

	final List<Predicate> predicates = new ArrayList<>();

	if (haveName(filter)) {
	    // where name like '%texto%'
	    final Expression<String> name = builder.parameter(String.class,
		    "NAME");
	    predicates.add(builder.like(builder.upper(root.get("name")), name));
	}

	if (haveSKU(filter)) {
	    final Expression<String> sku = builder.parameter(String.class,
		    "SKU");
	    predicates.add(builder.equal(root.get("sku"), sku));
	}

	criteriaQuery.where(predicates.toArray(new Predicate[0]));

	// adding order
	final List<Order> ordersBys = new ArrayList<>();
	ordersBys.add(builder.asc(root.get("name")));
	criteriaQuery.orderBy(ordersBys);

	final TypedQuery<Product> query = this.manager
		.createQuery(criteriaQuery);

	if (haveName(filter)) {
	    query.setParameter("NAME", "%"
		    + filter.getName().toUpperCase().trim() + "%");
	}
	if (haveSKU(filter)) {
	    query.setParameter("SKU", filter.getSku());
	}

	final List<Product> products = query.getResultList();

	return products;
    }

    /**
     * Gets the by sku.
     *
     * @param sku
     *            the sku
     * @return the by sku
     */
    public Product getBySKU(final String sku) {
	try {
	    final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
	    final CriteriaQuery<Product> criteriaQuery = builder
		    .createQuery(Product.class);
	    final Root<Product> root = criteriaQuery.from(Product.class);

	    criteriaQuery.select(root);
	    // criteriaQuery.where(builder.like(builder.upper(root.get("sku")),
	    // (sku.toUpperCase())));
	    criteriaQuery.where(builder.equal(builder.upper(root.get("sku")),
		    (sku.toUpperCase())));

	    final TypedQuery<Product> query = this.manager
		    .createQuery(criteriaQuery);
	    final Product p = query.getSingleResult();
	    return p;
	} catch (NoResultException | NonUniqueResultException e) {
	    return null;
	}

    }

    /**
     * Have sku.
     *
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getSku()} is diferent of null
     *         and is not empty, false otherwise.
     */
    private static boolean haveSKU(final ProductFilter filter) {
	return filter != null && filter.getSku() != null
		&& !filter.getSku().trim().isEmpty();
    }

    /**
     * Have name.
     *
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getName()} is diferent of null
     *         and is not empty, false otherwise.
     */
    private static boolean haveName(final ProductFilter filter) {
	return filter != null && filter.getName() != null
		&& !filter.getName().trim().isEmpty();
    }

    /**
     * Gets Product the by id.
     *
     * @param id
     *            the id
     * @return the by id
     */
    public Product getById(final Long id) {
	// can't not use the next line was implementation because
	// in that way the category and master category don't are load
	// return this.manager.find(Product.class, id);
	// --------------------------------------------------

	final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
	final CriteriaQuery<Product> criteriaQuery = builder
		.createQuery(Product.class);
	final Root<Product> root = criteriaQuery.from(Product.class);
	@SuppressWarnings({ "unchecked", "rawtypes", })
	final Join<Product, Category> productsCategoryJoin = (Join) root
		.fetch("category");
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	final Join<Category, Category> categorysJoin = (Join) productsCategoryJoin
		.fetch("masterCategory");

	criteriaQuery.select(root);
	// criteriaQuery.where(builder.like(builder.upper(root.get("sku")),
	// (sku.toUpperCase())));
	criteriaQuery.where(builder.equal(root.get("id"), id));

	final TypedQuery<Product> query = this.manager
		.createQuery(criteriaQuery);
	final Product product = query.getSingleResult();

	return product;
    }

    /**
     * Removes the product. This method must be call inside a transaction.
     *
     * @param product
     *            the product to remove Operation
     * @throws RepositoryException
     *             the repository exception
     */
    public void remove(final Product product) throws RepositoryException {
	try {
	    // the param product could't be attached (connected) in JPA Entity
	    // manager
	    // and so we get the product from the database
	    final Product productTemp = get(product.getId());

	    this.manager.remove(productTemp);

	    /*
	     * If we don't do flush the product will be only marked to
	     * exclusion, This exclusion will only be effective when performing
	     * a commit, or when you run the manual flush or an automatic flush,
	     * in this case do the manual. Flush will run the SQL delete, if the
	     * product is being used in some other table in the database a
	     * PersistenceException will be throw. (We whant co handler that
	     * exception over here, and not lose it to something out of control)
	     */
	    this.manager.flush();
	} catch (final PersistenceException e) {
	    throw new RepositoryException("Product can not be excluded.");
	}
    }

}
