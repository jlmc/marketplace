package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.entities.User_;
import org.xine.marketplace.model.filters.UserFilter;
import org.xine.marketplace.repository.exceptions.RepositoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
     * @param user
     *            the user
     * @return the user
     * @throws RepositoryException
     *             the repository exception
     */
    public User save(final User user) throws RepositoryException {
        try {
            return this.entityManager.merge(user);
        } catch (final OptimisticLockException e) {
            throw new RepositoryException(
                    "Concurrence error, the user has changed while you made the edit.", e,
                    RepositoryException.Type.CONCURRENCE);
        } catch (final PersistenceException e) {
            throw new RepositoryException("username or e-mail alredy in use.", e);
        }
    }

    /**
     * Delete.
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
            throw new RepositoryException("cant not delete the user", e.getCause());
        }
    }

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @return the by id
     */
    public User getById(final Long id) {
        return this.entityManager.find(User.class, id);
    }

    /**
     * Sets the entity manager.
     * @param entityManager
     *            the new entity manager
     */
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Gets the user by username.
     * @param email
     *            the email
     * @return the user by username
     */
    public User getUserByEmail(final String email) {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);
        criteriaQuery
                .where(builder.equal(builder.upper(root.get(User_.email)), email.toUpperCase()));

        final TypedQuery<User> userQuery = this.entityManager.createQuery(criteriaQuery);
        return CriteriaHelper.getSingleResultUncheck(userQuery);
    }

    /**
     * Gets the user by email.
     * @param email
     *            the email
     * @param fetchPermissions
     *            the fetch permissions
     * @return the user by email
     */
    public User getUserByEmail(final String email, final boolean fetchPermissions) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);

        if (fetchPermissions) {
            root.fetch(User_.permissions, JoinType.LEFT);
        }

        criteriaQuery.select(root).where(
                builder.equal(builder.upper(root.get(User_.email)), email.trim().toUpperCase()));

        final TypedQuery<User> userQuery = this.entityManager.createQuery(criteriaQuery);
        return CriteriaHelper.getSingleResultUncheck(userQuery);
    }

    /**
     * Have name.
     * @param filter
     *            the filter
     * @return true, if successful
     */
    private static boolean haveName(final UserFilter filter) {
        return filter != null && filter.getName() != null && !filter.getName().trim().isEmpty();
    }

    /**
     * Have email.
     * @param filter
     *            the filter
     * @return true, if successful
     */
    private static boolean haveEmail(final UserFilter filter) {
        return filter != null && filter.getEmail() != null && !filter.getEmail().trim().isEmpty();
    }

    /**
     * Search two.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<User> search(final UserFilter filter) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);

        if (isToLoadPermissions(filter)) {
            criteriaQuery.select(root).distinct(true);
            root.fetch(User_.permissions, JoinType.LEFT);
        } else {
            criteriaQuery.select(root);
        }

        final Map<String, Object> paramValues = new HashMap<>();

        // INF:: define predicates
        final List<Predicate> predicates = new ArrayList<>();

        if (haveName(filter)) {
            final Expression<String> nameParam = builder.parameter(String.class, "_paramName");
            predicates.add(builder.like(builder.upper(root.get(User_.username)), nameParam));

            paramValues
                    .put("_paramName", CriteriaHelper.MatchMode.ANYWHERE.toMatchString(filter
                            .getName().toUpperCase()));
        }
        if (haveEmail(filter)) {
            final Expression<String> email = builder.parameter(String.class, "_paramEmail");
            predicates.add(builder.equal(builder.upper(root.get(User_.email)), email));

            paramValues.put("_paramEmail", filter.getEmail().toUpperCase());
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // INF:: add Orders to criteriaQuery

        criteriaQuery.orderBy(builder.asc(builder.upper(root.get(User_.username))),
                builder.asc(root.get(User_.id)));

        final TypedQuery<User> typedQuery = this.entityManager.createQuery(criteriaQuery);
        // INFO:: add parameters to typedQuery

        paramValues.keySet().forEach(key -> {
            typedQuery.setParameter(key, paramValues.get(key));
        });

        final List<User> users = typedQuery.getResultList();
        return users;
    }

    /**
     * Checks if is to load permissions.
     * @param filter
     *            the filter
     * @return true, if is to load permissions
     */
    private static boolean isToLoadPermissions(final UserFilter filter) {
        return filter != null && filter.isLoadPermissions();
    }

}
