package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.exceptions.RepositoryException;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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

}
