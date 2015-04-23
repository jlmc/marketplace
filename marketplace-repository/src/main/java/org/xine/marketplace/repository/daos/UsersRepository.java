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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.UserFilter;
import org.xine.marketplace.repository.exceptions.RepositoryException;

/**
 * The Class UsersRepository.
 */
public class UsersRepository implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The entity manager. */
    @Inject
    private EntityManager entityManager;

    /**
     * Save or Update.
     *
     * @param user
     *            the user
     * @return the user
     * @throws RepositoryException
     *             the repository exception
     */
    public User save(final User user) throws RepositoryException {
	try {
	    return this.entityManager.merge(user);
	} catch (final PersistenceException e) {
	    throw new RepositoryException("username or e-mail alredy in use.",
		    e);
	}
    }

    /**
     * Delete.
     *
     * @param user
     *            the user
     * @throws RepositoryException
     *             the repository exception
     */
    public void delete(final User user) throws RepositoryException {
	try {
	    final User temp = this.entityManager.merge(user);
	    this.entityManager.remove(temp);
	    this.entityManager.flush();
	} catch (final PersistenceException e) {
	    throw new RepositoryException("cant not delete the user",
		    e.getCause());
	}
    }

    /**
     * Gets the by id.
     *
     * @param id
     *            the id
     * @return the by id
     */
    public User getById(final Long id) {
	return this.entityManager.find(User.class, id);
    }

    /**
     * Sets the entity manager.
     *
     * @param entityManager
     *            the new entity manager
     */
    public void setEntityManager(final EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    /**
     * Gets the user by username.
     *
     * @param email
     *            the email
     * @return the user by username
     */
    public User getUserByEmail(final String email) {
	final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
	final CriteriaQuery<User> criteriaQuery = builder
		.createQuery(User.class);
	final Root<User> root = criteriaQuery.from(User.class);

	criteriaQuery.select(root);
	criteriaQuery.where(builder.equal(builder.upper(root.get("email")),
		email.toUpperCase()));

	try {
	    final TypedQuery<User> userQuery = this.entityManager
		    .createQuery(criteriaQuery);
	    final User user = userQuery.getSingleResult();

	    return user;
	} catch (final NoResultException e) {
	    return null;
	} catch (final NonUniqueResultException e) {
	    return null;
	}
    }

    /**
     * Search.
     *
     * @param filter
     *            the filter
     * @return the list
     */
    public List<User> search(final UserFilter filter) {
	final CriteriaBuilder criteriaBuilder = this.entityManager
		.getCriteriaBuilder();
	final CriteriaQuery<User> criteriaQuery = criteriaBuilder
		.createQuery(User.class);

	final Root<User> root = criteriaQuery.from(User.class);
	final Join<User, Permission> userPermission = (Join) root.fetch(
		"permissions", JoinType.LEFT);
	root.join("permissions", JoinType.LEFT);

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// final Join<Product, Category> joiner = (Join) root.fetch("category");

	criteriaQuery.select(root).distinct(true);

	// predicates

	final List<Predicate> predicates = new ArrayList<>();

	if (haveName(filter)) {
	    // where name like '%texto%'
	    final Expression<String> name = criteriaBuilder.parameter(
		    String.class, "NAME");
	    predicates.add(criteriaBuilder.like(
		    criteriaBuilder.upper(root.get("username")), name));
	}

	// if (haveSKU(filter)) {
	// final Expression<String> sku = builder.parameter(String.class,
	// "SKU");
	// predicates.add(builder.equal(root.get("sku"), sku));
	// }

	criteriaQuery.where(predicates.toArray(new Predicate[0]));

	// adding order
	final List<Order> ordersBys = new ArrayList<>();
	ordersBys.add(criteriaBuilder.asc(criteriaBuilder.upper(root
		.get("username"))));
	criteriaQuery.orderBy(ordersBys);

	final TypedQuery<User> tq = this.entityManager
		.createQuery(criteriaQuery);

	if (haveName(filter)) {
	    tq.setParameter("NAME", "%" + filter.getName().toUpperCase().trim()
		    + "%");
	}

	final List<User> users = tq.getResultList();
	return users;
    }

    /**
     * Have name.
     *
     * @param filter
     *            the filter
     * @return true, if successful
     */
    private static boolean haveName(final UserFilter filter) {
	return filter != null && filter.getName() != null
		&& !filter.getName().trim().isEmpty();
    }

}
