package org.xine.marketplace.repository.integration;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.ClientType;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.filters.ClientFilter;
import org.xine.marketplace.repository.daos.ClientsRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The Class ClientIntegrationTest.
 */
@SuppressWarnings("static-method")
public class ClientIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/ClientIntegrationTest.xml");
        initEntityManager();
    }

    /**
     * Gets the by id test.
     * @return the by id test
     */
    @Test
    public void get_by_id_test() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(1L);

        final Client client = repository.getById(id);

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());
    }

    /**
     * Gets the by id with adreass test.
     * @return the by id with adreass test
     */
    @Test
    public void get_by_id_with_adreass_test() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(1L);

        final Client client = repository.getById(id, true);

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());

        Assert.assertEquals(2, client.getAddresses().size());

        if (!client.getAddresses().isEmpty()) {
            client.getAddresses().forEach(a -> {
                System.out.println(String.format("[%s \t %s]", a.getId(), a.getZipCode()));
            });
        }
    }

    /**
     * Gets the by id with out adreass test.
     * @return the by id with out adreass test
     */
    @Test
    public void get_by_id_without_adreass_test() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(2L);

        final Client client = repository.getById(id, true);

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(0, client.getAddresses().size());
    }

    /**
     * Gets the by id un exist.
     * @return the by id un exist
     */
    @Test
    public void get_by_id_un_exist() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(2222L);

        final Client client = repository.getById(id, true);

        Assert.assertNull(client);

    }

    @Test
    public void shearch_filter_null() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());
        final ClientFilter filter = null;

        final List<Client> cs = repository.shearch(filter);
        Assert.assertNotNull(cs);
        Assert.assertEquals(4, cs.size());

    }

    @Test
    public void shearch_filter_empty() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());
        final ClientFilter filter = new ClientFilter();

        final List<Client> cs = repository.shearch(filter);
        Assert.assertNotNull(cs);
        Assert.assertEquals(4, cs.size());

    }

    @Test
    public void shearch_filter_all() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());
        final ClientFilter filter = new ClientFilter();
        filter.setCode("6");
        filter.setName("Client");

        final List<Client> cs = repository.shearch(filter);
        Assert.assertNotNull(cs);
        Assert.assertEquals(2, cs.size());

    }

    @Test
    public void shearch_filter_all_and_type() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());
        final ClientFilter filter = new ClientFilter();
        filter.setCode("6");
        filter.setName("Client");
        filter.getTypes().add(ClientType.FOUNDATION);
        filter.getTypes().add(ClientType.PERSON);

        final List<Client> cs = repository.shearch(filter);
        Assert.assertNotNull(cs);
        Assert.assertEquals(2, cs.size());

    }

    //
    // private String sku;
    //
    // private String name;
    //
    // private BigDecimal unitValue;
    //
    // private Integer stockQty;
    @Test
    @Ignore
    public void testing_or() {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        final Root<Product> root = cq.from(Product.class);

        cq.select(root);

        final List<Predicate> predicates = new ArrayList<>();

        // where name like '%texto%'
        final Expression<String> name = builder.parameter(String.class, "NAME");
        predicates.add(builder.like(builder.upper(root.get("name")), name));

        final Expression<Integer> sku = builder.parameter(Integer.class, "QTY");
        final Expression<BigDecimal> unt = builder.parameter(BigDecimal.class, "U");
        predicates.add(

                builder.or(builder.equal(root.get("stockQty"), sku),
                        builder.lessThanOrEqualTo(root.get("unitValue"), unt)

                        ));

        cq.where(predicates.toArray(new Predicate[0]));

        // adding order

        final TypedQuery<Product> query = getEntityManager().createQuery(cq);
        query.setParameter("NAME", "%" + "test" + "%");
        query.setParameter("QTY", Integer.valueOf(100));
        query.setParameter("U", new BigDecimal(50000));

        final List<Product> products = query.getResultList();

    }
}
