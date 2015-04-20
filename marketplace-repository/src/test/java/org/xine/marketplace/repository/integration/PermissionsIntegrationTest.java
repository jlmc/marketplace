package org.xine.marketplace.repository.integration;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.repository.daos.PermissionsRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

import java.util.List;

/**
 * The Class PermissionsIntegrationTest.
 */
public class PermissionsIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/PermissionsIntegrationTest.xml");
        initEntityManager();
    }

    /**
     * Gets the all permissions test.
     * @return the all permissions test
     */
    @SuppressWarnings("static-method")
    @Test
    public void getAllPermissionsTest() {
        final PermissionsRepository repository = new PermissionsRepository();
        repository.setEntityManager(getEntityManager());
        // id="201" sku="20ABC"

        final List<Permission> ps = repository.getPermissions();

        Assert.assertNotNull(ps);
        Assert.assertEquals("should have 4 registers", 4, ps.size());
    }

    /**
     * Gets the permission by id test.
     * @return the permission by id test
     */
    @SuppressWarnings({"static-method", "boxing" })
    @Test
    public void getPermissionByIdTest() {
        final PermissionsRepository repository = new PermissionsRepository();
        repository.setEntityManager(getEntityManager());

        final Permission p = repository.getPermissionById(1L);

        Assert.assertNotNull(p);
    }
}
