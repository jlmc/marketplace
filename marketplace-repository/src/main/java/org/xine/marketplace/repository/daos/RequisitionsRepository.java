package org.xine.marketplace.repository.daos;

import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.repository.daos.Helper.MatchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
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
 * The Class RequisitionsRepository.
 */
public class RequisitionsRepository implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The entity manager. */
    @Inject
    private EntityManager entityManager;

    //

    /**
     * Save.
     * @param requisition
     *            the requisition
     * @return the requisition
     */
    public Requisition save(final Requisition requisition) {
        return this.entityManager.merge(requisition);
    }

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Requisition> search(final RequisitionFilter filter) {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Requisition> criteriaQuery = builder.createQuery(Requisition.class);
        final Root<Requisition> root = criteriaQuery.from(Requisition.class);

        criteriaQuery.select(root);
        @SuppressWarnings({"unchecked", "rawtypes" })
        final Join<Requisition, Client> joinerClients = (Join) root.fetch("client");
        @SuppressWarnings({"unchecked", "rawtypes" })
        final Join<Requisition, User> JoinSellers = (Join) root.fetch("seller");

        //
        final List<Predicate> predicates = new ArrayList<>();

        final LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        if (filter != null) {

            if (filter.getNumberStart() != null) {
                final Expression<Long> ns = builder.parameter(Long.class, "_ns");
                predicates.add(builder.greaterThanOrEqualTo(root.get("id"), ns));

                parameters.put("_ns", filter.getNumberStart());
            }
            if (filter.getNumberEnd() != null) {
                final Expression<Long> ne = builder.parameter(Long.class, "_ne");
                predicates.add(builder.lessThanOrEqualTo(root.get("id"), ne));

                parameters.put("_ne", filter.getNumberEnd());
            }
            if (filter.getCreationDateStart() != null) {
                final Expression<Date> ds = builder.parameter(Date.class, "_ds");
                predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), ds));

                parameters.put("_ds", filter.getCreationDateStart());
            }
            if (filter.getCreationDateEnd() != null) {
                final Expression<Date> de = builder.parameter(Date.class, "_de");
                predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), de));

                parameters.put("_de", filter.getCreationDateEnd());
            }
            if (!Strings.isNullOrBlank(filter.getSellerName())) {
                // final Expression<String> sn = builder.parameter(String.class, "_sn");
                // // it is possible use the root or the joinseller in this case, the result is
                // will
                // // be the same
                // // predicates.add(builder.like(root.get("seller").get("username"), sn));
                // predicates.add(builder.like(builder.upper(JoinSellers.get("username")), sn));
                // parameters.put("_sn", "%" + filter.getSellerName().trim().toUpperCase());

                predicates.add(Helper.ilike(builder, JoinSellers.get("username"),
                        filter.getSellerName(), MatchMode.ANYWHERE));
            }
            if (!Strings.isNullOrBlank(filter.getClientName())) {
                predicates.add(Helper.ilike(builder, joinerClients.get("name"),
                        filter.getClientName(), MatchMode.ANYWHERE));
            }
            if (filter.getStatus() != null && filter.getStatus().length > 0) {
                final List<Predicate> sts = new ArrayList<>();
                Arrays.stream(filter.getStatus()).forEach(
                        s -> sts.add(builder.equal(root.get("status"), s)));
                final Predicate statusPredicate = builder.or(sts.toArray(new Predicate[0]));
                predicates.add(statusPredicate);
            }

        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(builder.desc(root.get("id")));

        final TypedQuery<Requisition> tquery = this.entityManager.createQuery(criteriaQuery);
        //
        // definition of the parameters

        parameters.keySet().forEach(key -> {
            tquery.setParameter(key, parameters.get(key));
        });

        // for (final String key : parms.keySet()) {
        // tquery.setParameter(key, "%" + parms.get(key));
        // }
        //
        //
        return tquery.getResultList();

    }

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @return the by id
     */
    public Requisition getById(final Long id) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Requisition> criteriaQuery = builder.createQuery(Requisition.class);
        final Root<Requisition> root = criteriaQuery.from(Requisition.class);

        criteriaQuery.select(root);
        @SuppressWarnings({"unchecked", "rawtypes", "unused" })
        final Join<Requisition, Client> joinerClients = (Join) root.fetch("client");
        @SuppressWarnings({"unchecked", "rawtypes", "unused" })
        final Join<Requisition, User> JoinSellers = (Join) root.fetch("seller");

        // load the itens
        root.fetch("requisitionItens", JoinType.LEFT);

        criteriaQuery.where(builder.equal(root.get("id"), id));

        final TypedQuery<Requisition> query = this.entityManager.createQuery(criteriaQuery);

        // query.getSingleResult();
        final Requisition req = Helper.getSingleResultUncheck(query);
        return req;
    }

    /**
     * Sets the entity manager.
     * @param entityManager
     *            the entityManager to set
     */
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
