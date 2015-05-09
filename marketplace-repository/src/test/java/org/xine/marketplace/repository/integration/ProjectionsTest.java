package org.xine.marketplace.repository.integration;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.filters.RequisitionActivityFilter;
import org.xine.marketplace.repository.daos.RequisitionsRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProjectionsTest extends AbstractDbUnitJpaTest {
    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/ProjectionTest.xml");
        initEntityManager();
    }

    @Test
    public void testOne() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Date> criteriaQuery = builder.createQuery(Date.class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.select(req.<Date> get("creationDate"));

        final TypedQuery<Date> typedQuery = em.createQuery(criteriaQuery);

        final List<Date> dates = typedQuery.getResultList();

        dates.forEach(System.out::println);

    }

    @Test
    public void testOne_2() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.multiselect(req.<Date> get("creationDate"),
                req.<BigDecimal> get("totalValue"));

        final TypedQuery<Object[]> typedQuery = em.createQuery(criteriaQuery);

        final List<Object[]> dates = typedQuery.getResultList();

        dates.forEach(a -> {
            System.out.println(String.format("%s \t %f", a[0], a[1]));
        });

    }

    @Test
    public void testOne_3() {

        final Client client = new Client();
        client.setId(1L);

        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.multiselect(req.<Date> get("creationDate"),
                builder.sum(req.<BigDecimal> get("totalValue")));

        criteriaQuery.where(builder.equal(req.get("client"), client));

        criteriaQuery.groupBy(req.<Date> get("creationDate"));

        criteriaQuery.orderBy(builder.asc(req.<Date> get("creationDate")));

        final TypedQuery<Object[]> typedQuery = em.createQuery(criteriaQuery);

        final List<Object[]> dates = typedQuery.getResultList();

        dates.forEach(a -> {
            System.out.println(String.format("%s \t %f", a[0], a[1]));
        });

    }

    @Test
    public void testOne_3_Tupla() {

        final Client client = new Client();
        client.setId(1L);

        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.multiselect(req.<Date> get("creationDate").alias("D"),
                builder.sum(req.<BigDecimal> get("totalValue")).alias("T"));

        criteriaQuery.where(builder.equal(req.get("client"), client));

        criteriaQuery.groupBy(req.<Date> get("creationDate"));

        criteriaQuery.orderBy(builder.asc(req.<Date> get("creationDate")));

        final TypedQuery<Tuple> typedQuery = em.createQuery(criteriaQuery);

        final List<Tuple> dates = typedQuery.getResultList();

        dates.forEach(a -> {
            System.out.println(String.format("%s \t %f", a.get("D"), a.get("T")));
        });

    }

    @Test
    public void testOne_3_Construtores() {

        final Client client = new Client();
        client.setId(1L);

        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<ChartDateValue> criteriaQuery = builder
                .createQuery(ChartDateValue.class);

        final Root<Requisition> req = criteriaQuery.from(Requisition.class);

        criteriaQuery.select(builder.construct(ChartDateValue.class,

                req.<Date> get("creationDate"), builder.sum(req.<BigDecimal> get("totalValue"))

                ));

        criteriaQuery.where(builder.equal(req.get("client"), client));

        criteriaQuery.groupBy(req.<Date> get("creationDate"));

        criteriaQuery.orderBy(builder.asc(req.<Date> get("creationDate")));

        final TypedQuery<ChartDateValue> typedQuery = em.createQuery(criteriaQuery);

        final List<ChartDateValue> dates = typedQuery.getResultList();

        dates.forEach(a -> {
            System.out.println(String.format("%s \t %f", a.getDate(), a.getValue()));
        });

    }

    @Test
    public void testRequisitionTotal() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final Map<Date, BigDecimal> result = repository
                .getTotalByDate(new RequisitionActivityFilter(null, null, 15));

        result.keySet()
        .stream()
        .forEachOrdered(
                c -> System.out.println(String.format("%s \t %f", c, result.get(c))));

    }

}

class ChartDateValue {
    private Date date;
    private BigDecimal value;

    public ChartDateValue(final Date date, final BigDecimal value) {
        super();
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }

}
