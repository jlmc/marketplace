package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.Address;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
        // we must set JoinType.LEFT because of the clients that don't have
        // addresses defined
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
     * Save client.
     * @param client
     *            the client
     * @return the client
     */
    public Client saveClient(final Client client) {
        return this.entityManager.merge(client);
    }

    /**
     * Shearch.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Client> shearch(final ClientFilter filter) {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> cQuery = builder.createQuery(Client.class);
        final Root<Client> root = cQuery.from(Client.class);

        cQuery.select(root);

        final List<Predicate> predicates = new ArrayList<>();

        final Expression<String> nameExpression = builder.parameter(String.class, "_Name");
        final Expression<String> codeExpression = builder.parameter(String.class, "_Code");

        if (filter != null) {
            if (!Strings.isNullOrBlank(filter.getName())) {
                predicates.add(builder.like(builder.upper(root.get("name")), nameExpression));
            }
            if (!Strings.isNullOrBlank(filter.getCode())) {
                predicates.add(builder.like(builder.upper(root.get("cnjp")), codeExpression));
            }
            if (!filter.getTypes().isEmpty()) {
                final ArrayList<Predicate> typesOrs = new ArrayList<>();
                filter.getTypes().forEach(
                        t -> typesOrs.add(builder.equal(root.get("clientType"), t)));

                predicates.add(builder.or(typesOrs.toArray(new Predicate[0])));
            }

        }

        cQuery.where(predicates.toArray(new Predicate[0]));

        final TypedQuery<Client> query = this.entityManager.createQuery(cQuery);

        if (filter != null) {
            if (!Strings.isNullOrBlank(filter.getName())) {
                query.setParameter("_Name", "%" + filter.getName().trim().toUpperCase() + "%");
            }
            if (!Strings.isNullOrBlank(filter.getCode())) {
                query.setParameter("_Code", "%" + filter.getCode().trim().toUpperCase() + "%");
            }

        }

        return query.getResultList();
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
