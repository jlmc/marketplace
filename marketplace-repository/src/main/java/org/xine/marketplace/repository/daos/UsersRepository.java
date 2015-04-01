package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.User;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The Class UsersRespository.
 */
public class UsersRepository {

    /** The manager. */
    @Inject
    private EntityManager manager;

    /**
     * Gets the list of user.
     * @return the list of user
     */
    public List<User> getListOfUser() {
        final CriteriaBuilder cb = this.manager.getCriteriaBuilder();
        final CriteriaQuery<User> cq = cb.createQuery(User.class);
        final Root<User> rootEntry = cq.from(User.class);
        final CriteriaQuery<User> all = cq.select(rootEntry);
        final TypedQuery<User> allQuery = this.manager.createQuery(all);
        return allQuery.getResultList();
    }

    /**
     * Gets the list of user jpql.
     * @return the list of user jpql
     */
    public List<User> getListOfUserJPQL() {
        return this.manager.createQuery("FROM User").getResultList();
    }

    /**
     * Gets the list of user jpql.
     * @return the list of user jpql
     */
    public User getUserbyUsernameJPQL(final String username) {
        return (User) this.manager.createQuery("FROM User WHERE username = :username")
                .setParameter("username", username).getSingleResult();
    }

    public User getUserbyUsername(final String username) {
        final CriteriaBuilder cb = this.manager.getCriteriaBuilder();

        final CriteriaQuery<User> cq = cb.createQuery(User.class);
        final Root<User> pet = cq.from(User.class);
        cq.where(cb.equal(pet.get("username"), username));

        return this.manager.createQuery(cq).getSingleResult();

    }

    /**
     * Gets the user.
     * @param id
     *            the id
     * @return the user
     */
    public User getUser(final Long id) {

        return this.manager.find(User.class, id);
    }

    /**
     * Sets the manager.
     * @param manager
     *            the new manager
     */
    public void setManager(final EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Save user.
     * @param user
     *            the user
     * @return the user
     */
    public User saveUser(final User user) {
        // this.manager.merge(user);
        this.manager.merge(user);

        return user;
    }


    /**
     * Delet user.
     * @param user
     *            the user
     */
    public void deletUser(final User user) {
        final User u = this.manager.merge(user);

        this.manager.remove(u);

    }

}
