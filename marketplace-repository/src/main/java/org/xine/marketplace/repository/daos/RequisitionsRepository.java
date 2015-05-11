package org.xine.marketplace.repository.daos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionItem;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.RequisitionActivityFilter;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.model.vo.DateValue;
import org.xine.marketplace.repository.daos.Helper.MatchMode;
import org.xine.marketplace.util.DateUtils;

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

                predicates.add(Helper.ilike(builder, JoinSellers.get("username"), filter.getSellerName(), MatchMode.ANYWHERE));
            }
            if (!Strings.isNullOrBlank(filter.getClientName())) {
                predicates.add(Helper.ilike(builder, joinerClients.get("name"), filter.getClientName(), MatchMode.ANYWHERE));
            }
            if (filter.getStatus() != null && filter.getStatus().length > 0) {
                final List<Predicate> sts = new ArrayList<>();
                Arrays.stream(filter.getStatus()).forEach(s -> sts.add(builder.equal(root.get("status"), s)));
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
        @SuppressWarnings({"unchecked", "rawtypes" })
        final Join<Requisition, RequisitionItem> reqItens = (Join) root.fetch("requisitionItens", JoinType.LEFT);
        reqItens.fetch("product");

        criteriaQuery.where(builder.equal(root.get("id"), id));

        final TypedQuery<Requisition> query = this.entityManager.createQuery(criteriaQuery);

        // query.getSingleResult();
        final Requisition req = Helper.getSingleResultUncheck(query);
        return req;
    }

    /**
     * Gets the total by date.
     * @param filter
     *            the filter
     * @return the total by date
     */
    @SuppressWarnings("boxing")
    public Map<Date, BigDecimal> getTotalByDate(final RequisitionActivityFilter filter) {

        final LocalDate endAt = LocalDate.now();
        final LocalDate startAt = endAt.minusDays(filter.getNumberOfDays());
        final Map<Date, BigDecimal> map = createEmptyDateMap(filter.getNumberOfDays(), startAt);

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DateValue> criteriaQuery = builder.createQuery(DateValue.class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.select(builder.construct(DateValue.class, req.<Date> get("creationDate"), builder.sum(req.<BigDecimal> get("totalValue"))));

        final List<Predicate> restrictions = new ArrayList<>();

        if (filter.isSeller()) {
            restrictions.add(builder.equal(req.get("seller"), filter.getSeller()));
        }
        if (filter.isClient()) {
            restrictions.add(builder.equal(req.get("client"), filter.getClient()));
        }
        if (filter.isNumberOfDays()) {
            // final ParameterExpression<Date> start = builder.parameter(Date.class, "_dt1");
            // final ParameterExpression<Date> end = builder.parameter(Date.class, "_dt2");
            restrictions.add(builder.between(req.<Date> get("creationDate"), DateUtils.asDate(startAt), DateUtils.asDate(endAt)));
        }

        criteriaQuery.where(restrictions.toArray(new Predicate[0]));

        criteriaQuery.groupBy(req.<Date> get("creationDate"));
        criteriaQuery.orderBy(builder.asc(req.<Date> get("creationDate")));

        final TypedQuery<DateValue> typedQuery = this.entityManager.createQuery(criteriaQuery);
        final List<DateValue> results = typedQuery.getResultList();

        results.forEach(dv -> map.put(dv.getDate(), dv.getValue()));

        return map;
    }

    /**
     * Creates the empty date map.
     * @param numberOfDays
     *            the number of days
     * @param startAt
     *            the start at
     * @return the map
     */
    private static Map<Date, BigDecimal> createEmptyDateMap(final Integer numberOfDays, final LocalDate startAt) {
        final Map<Date, BigDecimal> map = new TreeMap<>();
        LocalDate s = startAt;
        for (int i = 0; i < numberOfDays.intValue(); i++) {
            map.put(DateUtils.asDate(s), BigDecimal.ZERO);
            s = s.plusDays(1);
        }
        return map;
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
