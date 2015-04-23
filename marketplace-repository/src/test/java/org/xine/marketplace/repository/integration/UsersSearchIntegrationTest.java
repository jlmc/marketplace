package org.xine.marketplace.repository.integration;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.UserFilter;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

import java.util.List;

public class UsersSearchIntegrationTest extends AbstractDbUnitJpaTest {

    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/UsersSearchIntegrationTest.xml");
        initEntityManager();
    }

    @Test
    public void searchBasicTest() {
        final UsersRepository repository = new UsersRepository();
        repository.setEntityManager(getEntityManager());

        final List<User> r = repository.search(null);

        r.forEach(u -> {
            System.out.println(String.format("user -> [%d \t  %s \t]", u.getId(), u.getUsername()));

        });

        Assert.assertNotNull(r);
        Assert.assertEquals(4, r.size());
    }

    @Test
    public void searchSendingNameTest() {
        final UsersRepository repository = new UsersRepository();
        repository.setEntityManager(getEntityManager());

        final List<User> r = repository.search(new UserFilter("Abc", null));

        r.forEach(u -> {
            System.out.println(String.format("user -> [%d \t  %s \t ]", u.getId(), u.getUsername()));

        });

        Assert.assertNotNull(r);
        Assert.assertEquals(3, r.size());
    }

}
