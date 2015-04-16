package org.xine.marketplace.repository.integration;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.repository.daos.CategorysRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

/**
 * The Class CategoryIntegrationTest.
 */
@SuppressWarnings({"static-method", "boxing" })
public class CategoryIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/Category.xml");
        initEntityManager();
    }

    /**
     * select category0_.id as id1_3_0_, category0_.description as
     * descript2_3_0_, category0_.masterCategory as masterCa3_3_0_
     * from category category0_
     * where category0_.id=?.
     */

    @Test
    public void testFind() {

        final CategorysRepository repository = new CategorysRepository();
        repository.setManager(getEntityManager());

        final Category category = repository.getById(1L);

        Assert.assertNotNull(category);

    }

    /**
     * Gets the child of master category.
     * @return the child of master category
     */
    @Test
    public void getChildOfMasterCategory() {
        final Category c = new Category();
        c.setId(1L);

        final CategorysRepository repository = new CategorysRepository();
        repository.setManager(getEntityManager());

        final List<Category> subCategories = repository.getChildCategorys(c);

        Assert.assertEquals("should have 4 registers", 4, subCategories.size());
    }

    /**
     * Gets the master category.
     * @return the master category
     */
    @Test
    public void getMasterCategory() {

        final CategorysRepository repository = new CategorysRepository();
        repository.setManager(getEntityManager());

        final List<Category> categorys = repository.getRootCategorys();

        Assert.assertEquals("should have 4 registers", 4, categorys.size());
    }

}
