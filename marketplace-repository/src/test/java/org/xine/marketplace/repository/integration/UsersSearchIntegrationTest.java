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

/**
 * The Class UsersSearchIntegrationTest.
 */
@SuppressWarnings("static-method")
public class UsersSearchIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    @BeforeClass
    public static void init() throws HibernateException, DatabaseUnitException {
        AbstractDbUnitJpaTest.setDataSetpath("xml/UsersSearchIntegrationTest.xml");
        initEntityManager();
    }

    /**
     * Search basic test.
     */

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

    /**
     * Search sending name test.
     */
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

    /**
     * Search sending two name test.
     */
    @Test
    public void searchSendingTwoNameTest() {
        final UsersRepository repository = new UsersRepository();
        repository.setEntityManager(getEntityManager());

        final List<User> r = repository.search(new UserFilter("Abc", null));

        r.forEach(u -> {
            System.out.println(String.format("user -> [%d \t  %s \t ]", u.getId(), u.getUsername()));

        });

        Assert.assertNotNull(r);
        Assert.assertEquals(3, r.size());
    }

    /**
     * Search sending two name and email test.
     */
    @Test
    public void searchSendingTwoNameAndEmailTest() {
        final UsersRepository repository = new UsersRepository();
        repository.setEntityManager(getEntityManager());

        final List<User> r = repository.search(new UserFilter("Abc", "emailThree@"));

        r.forEach(u -> {
            System.out.println(String.format("user -> [%d \t  %s \t ]", u.getId(), u.getUsername()));

        });

        Assert.assertNotNull(r);
        Assert.assertEquals(1, r.size());
    }

    /**
     * Search with permission test.
     */
    @Test
    public void searchWithPermissionTest() {
        final UsersRepository repository = new UsersRepository();
        repository.setEntityManager(getEntityManager());

        final UserFilter filter = new UserFilter();
        filter.setLoadPermissions(true);
        final List<User> users = repository.search(filter);

        users.forEach(u -> {
            System.out.println(String.format("user -> [%d \t  %s \t ]", u.getId(), u.getUsername()));

        });

        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());

        users.stream().filter(u -> u.getId().equals(Long.valueOf(1L))).findAny().ifPresent(u -> {
            System.out.println("User 1 Must have 3 Permissions");
            Assert.assertEquals(3, u.getPermissions().size());
        });
        users.stream().filter(u -> u.getId().equals(Long.valueOf(2L))).findAny().ifPresent(u -> {
            System.out.println("User 2 Must have 1 Permissions");
            Assert.assertEquals(1, u.getPermissions().size());
        });
        users.stream().filter(u -> u.getId().equals(Long.valueOf(3L))).findAny().ifPresent(u -> {
            System.out.println("User 3 Must have 1 Permissions");
            Assert.assertEquals(1, u.getPermissions().size());
        });

        // users.stream().filter(u -> u.getId().equals(Long.valueOf(1L)))
        // .forEach(u -> System.out.println("SIZE: " + u.getPermissions().size()));
        // users.stream().filter(u -> u.getId().equals(Long.valueOf(2L)))
        // .forEach(u -> System.out.println("SIZE: " + u.getPermissions().size()));
        // users.stream().filter(u -> u.getId().equals(Long.valueOf(3L)))
        // .forEach(u -> System.out.println("SIZE: " + u.getPermissions().size()));

    }
}
