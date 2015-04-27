package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.Address;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * The Class ClientsRepository.
 */
public class ClientsRepository implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The entity manager. */
    @Inject
    private EntityManager entityManager;

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @return the by id
     */
    public Client getById(final Long id) {
        return this.entityManager.find(Client.class, id);
    }

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @param withAdreass
     *            the with adreass
     * @return the by id
     */
    public Client getById(final Long id, final boolean withAdreass) {
        if (!withAdreass) {
            return this.getById(id);
        }

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);

        final Root<Client> root = criteriaQuery.from(Client.class);

        // final Join<Client, Address> joiner = (Join) root.fetch("addresses");
        // we must set JoinType.LEFT because of the clients that don't have addresses defined
        @SuppressWarnings({"unchecked", "rawtypes", "unused" })
        final Join<Client, Address> joiner = (Join) root.fetch("addresses", JoinType.LEFT);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

        final TypedQuery<Client> query = this.entityManager.createQuery(criteriaQuery);

        // query.getSingleResult();
        final Client c = Helper.getSingleResultUncheck(query);

        return c;
    }

    /**
     * Save.
     * @param client
     *            the client
     * @return the client
     */
    public Client save(final Client client) {
        return null;
    }

    /**
     * Shearch.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Client> shearch(final ClientFilter filter) {
        return null;
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
