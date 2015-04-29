package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.Address;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;
import org.xine.marketplace.repository.exceptions.RepositoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
     * Save.
     * @param client
     *            the client
     * @return the client
     */
    public Client save(final Client client) {
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
     * Shearch.
     * @param code
     *            the code
     * @param email
     *            the email
     * @return the list
     */
    public List<Client> shearch(final String code, final String email) {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> cQuery = builder.createQuery(Client.class);
        final Root<Client> root = cQuery.from(Client.class);

        cQuery.select(root);

        final List<Predicate> predicates = new ArrayList<>();

        final Expression<String> nameExpression = builder.parameter(String.class, "_Email");
        final Expression<String> codeExpression = builder.parameter(String.class, "_Code");

        if (!Strings.isNullOrBlank(email)) {
            predicates.add(builder.like(builder.upper(root.get("email")), nameExpression));
        }
        if (!Strings.isNullOrBlank(code)) {
            predicates.add(builder.like(builder.upper(root.get("cnjp")), codeExpression));
        }

        cQuery.where(builder.or(predicates.toArray(new Predicate[0])));

        final TypedQuery<Client> query = this.entityManager.createQuery(cQuery);

        if (!Strings.isNullOrBlank(email)) {
            query.setParameter("_Email", email.trim().toUpperCase());
        }
        if (!Strings.isNullOrBlank(code)) {
            query.setParameter("_Code", code.trim().toUpperCase());
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

    /**
     * Delete.
     * @param client
     *            the client
     * @throws RepositoryException
     *             the repository exception
     */
    public void delete(final Client client) throws RepositoryException {
        try {
            // the param product could't be attached (connected) in JPA Entity
            // manager
            // and so we get the Client from the database
            final Client clientTemp = this.getById(client.getId());

            this.entityManager.remove(clientTemp);

            /*
             * If we don't do flush the product will be only marked to
             * exclusion, This exclusion will only be effective when performing
             * a commit, or when you run the manual flush or an automatic flush,
             * in this case do the manual. Flush will run the SQL delete, if the
             * product is being used in some other table in the database a
             * PersistenceException will be throw. (We whant co handler that
             * exception over here, and not lose it to something out of control)
             */
            this.entityManager.flush();
        } catch (final PersistenceException e) {
            throw new RepositoryException("Product can not be excluded.");
        }

    }

}
