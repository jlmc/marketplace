package org.xine.marketplace.repository.integration;

import java.util.Calendar;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.model.filters.RequisitionFilterOrderBy;
import org.xine.marketplace.model.filters.RequisitionFilterOrderBy.RequisitionFilterOrderByType;
import org.xine.marketplace.repository.daos.RequisitionsRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

@SuppressWarnings({"static-method", "boxing" })
public class RequisitionsIntegrationTest extends AbstractDbUnitJpaTest {
    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/RequisitionsIntegrationTest.xml");
        initEntityManager();
    }

    @Test
    public void searchBasic() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final List<Requisition> reqs = repository.search(null);

        Assert.assertNotNull(reqs);
        Assert.assertEquals(1, reqs.size());
        System.out.println(reqs);

    }

    @Test
    public void searchWithIdLimitation() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final RequisitionFilter filter = new RequisitionFilter();
        filter.setNumberStart(0L);
        filter.setNumberEnd(100L);

        final Calendar ds = Calendar.getInstance();
        ds.set(2000, 5, 1);
        final Calendar dse = Calendar.getInstance();
        dse.set(2020, 5, 1);

        filter.setCreationDateEnd(dse.getTime());
        filter.setCreationDateStart(ds.getTime());
        filter.setSellerName("XXXAbcZZZZ");
        filter.setClientName("Cesario");
        filter.setStatus(new RequisitionStatus[] {RequisitionStatus.BUDGET, RequisitionStatus.CANCELLED, RequisitionStatus.ISSUED });

        final List<Requisition> reqs = repository.search(filter);

        Assert.assertNotNull(reqs);
        Assert.assertEquals(1, reqs.size());
        // System.out.println(reqs);

    }

    @Test
    public void testfilterCount() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final Long count = repository.countFilters(null);

        System.out.println("TOTAL: " + count);
    }

    @Test
    public void testfilterCount2() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final RequisitionFilter filter = new RequisitionFilter();
        filter.setNumberStart(0L);
        filter.setNumberEnd(100L);

        final Calendar ds = Calendar.getInstance();
        ds.set(2000, 5, 1);
        final Calendar dse = Calendar.getInstance();
        dse.set(2020, 5, 1);

        filter.setCreationDateEnd(dse.getTime());
        filter.setCreationDateStart(ds.getTime());
        filter.setSellerName("XXXAbcZZZZ");
        filter.setClientName("Cesario");
        filter.setStatus(new RequisitionStatus[] {RequisitionStatus.BUDGET, RequisitionStatus.CANCELLED, RequisitionStatus.ISSUED });

        filter.setFirstResult(1);
        filter.setMaxResults(15);

        final RequisitionFilterOrderBy orderBy = new RequisitionFilterOrderBy(RequisitionFilterOrderByType.SELLER_NAME, false);
        filter.setOrderBy(orderBy);

        final Long count = repository.countFilters(filter);

        System.out.println("TOTAL: " + count);
    }

    @Test
    public void testfilters() {
        final RequisitionsRepository repository = new RequisitionsRepository();
        repository.setEntityManager(getEntityManager());

        final RequisitionFilter filter = new RequisitionFilter();
        filter.setNumberStart(0L);
        filter.setNumberEnd(100L);

        final Calendar ds = Calendar.getInstance();
        ds.set(2000, 5, 1);
        final Calendar dse = Calendar.getInstance();
        dse.set(2020, 5, 1);

        filter.setCreationDateEnd(dse.getTime());
        filter.setCreationDateStart(ds.getTime());
        filter.setSellerName("XXXAbcZZZZ");
        filter.setClientName("Cesario");
        filter.setStatus(new RequisitionStatus[] {RequisitionStatus.BUDGET, RequisitionStatus.CANCELLED, RequisitionStatus.ISSUED });

        filter.setFirstResult(1);
        filter.setMaxResults(15);

        final RequisitionFilterOrderBy orderBy = new RequisitionFilterOrderBy(RequisitionFilterOrderByType.VALUE, false);
        filter.setOrderBy(orderBy);

        final List<Requisition> list = repository.filters(filter);

        System.out.println("TOTAL: " + list.size());
    }

}
