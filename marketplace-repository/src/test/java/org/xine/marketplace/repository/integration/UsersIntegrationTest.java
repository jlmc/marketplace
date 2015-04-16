package org.xine.marketplace.repository.integration;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

/**
 * The Class UsersIntegrationTest.
 */
@SuppressWarnings("static-method")
public class UsersIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/User.xml");
        initEntityManager();
    }

    /**
     * select
     * user0_.id as id1_2_,
     * user0_.email as email2_2_,
     * user0_.password as password3_2_,
     * user0_.username as username4_2_
     * from
     * User user0_
     * @return the all test
     */

    @Test
    public void getAllTest() {
        // given
        final EntityManager manager = getEntityManager();

        final CriteriaBuilder builder = manager.getCriteriaBuilder();

        final CriteriaQuery<User> cq = builder.createQuery(User.class);

        final Root<User> root = cq.from(User.class);
        cq.select(root);

        final TypedQuery<User> query = manager.createQuery(cq);

        final List<User> users = query.getResultList();

        Assert.assertEquals("should have 4 registers", 4, users.size());
    }
}
