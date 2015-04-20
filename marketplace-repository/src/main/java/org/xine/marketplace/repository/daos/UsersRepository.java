package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.exceptions.RepositoryException;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        // TODO::missing implemetation
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
        criteriaQuery.where(builder.equal(builder.upper(root.get("email")), email.toUpperCase()));

        try {
            final TypedQuery<User> userQuery = this.entityManager.createQuery(criteriaQuery);
            final User user = userQuery.getSingleResult();

            return user;
        } catch (final NoResultException e) {
            return null;
        } catch (final NonUniqueResultException e) {
            return null;
        }
    }

}
