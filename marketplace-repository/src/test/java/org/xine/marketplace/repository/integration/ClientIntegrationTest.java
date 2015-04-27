package org.xine.marketplace.repository.integration;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.repository.daos.ClientsRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

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

    @Test
    public void getByIdTest() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(1L);

        final Client client = repository.getById(id);

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());
    }

    @Test
    public void getByIdWithAdreassTest() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(1L);

        final Client client = repository.getById(id, true);
        // final Set<Address> adds = client.getAddresses();

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());

        Assert.assertEquals(2, client.getAddresses().size());
        // Assert.assertEquals(2, adds.size());

        if (!client.getAddresses().isEmpty()) {
            client.getAddresses().forEach(a -> {
                System.out.println(String.format("[%s \t %s]", a.getId(), a.getZipCode()));
            });
        }
    }

    @Test
    public void getByIdWithOutAdreassTest() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(2L);

        final Client client = repository.getById(id, true);
        // final Set<Address> adds = client.getAddresses();

        Assert.assertNotNull(client);
        Assert.assertEquals(id, client.getId());

        Assert.assertEquals(0, client.getAddresses().size());
        // Assert.assertEquals(2, adds.size());
    }

    @Test
    public void getByIdUnExist() {
        final ClientsRepository repository = new ClientsRepository();
        repository.setEntityManager(getEntityManager());

        final Long id = Long.valueOf(2222L);

        final Client client = repository.getById(id, true);
        // final Set<Address> adds = client.getAddresses();

        Assert.assertNull(client);

    }

}
