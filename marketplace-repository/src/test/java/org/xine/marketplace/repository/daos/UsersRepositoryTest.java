package org.xine.marketplace.repository.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.util.Constants;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * The Class UsersRepositoryTest.
 */
public class UsersRepositoryTest {

    /** The entity manager. */
    private EntityManager entityManager;

    /** The users repository. */
    private UsersRepository usersRepository;

    /** The helper. */
    JIntegrity helper = new JIntegrity();

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {

        this.helper.cleanAndInsert();

        this.entityManager = JPAHelper.entityManagerFactory(Constants.PERSISTENCE_UNIT_NAME)
                .createEntityManager();
        this.usersRepository = new UsersRepository();
        this.usersRepository.setManager(this.entityManager);
    }

    /**
     * After.
     */
    @After
    public void after() {
        this.entityManager.close();
    }

    /**
     * Test get list of user.
     */
    @Test
    public void testGetListOfUser() {
        final List<User> usersList = this.usersRepository.getListOfUser();

        assertNotNull(usersList);
        final int size = usersList.size();

        assertEquals(4, size);
    }

    /**
     * Test get list of user jpql.
     */
    @Test
    public void testGetListOfUserJPQL() {
        final List<User> usersList = this.usersRepository.getListOfUserJPQL();

        assertNotNull(usersList);
        final int size = usersList.size();

        assertEquals(4, size);
    }

    /**
     * Test get user.
     */
    @Test
    // @Ignore
    public void testGetUser() {
        final User user = this.usersRepository.getUser(Long.valueOf(1L));

        assertNotNull(user);
    }

    @Test
    public void getUserByUsername() {
        // final User u = this.usersRepository.getUserbyUsernameJPQL("userOne");
        final User u = this.usersRepository.getUserbyUsername("userOne");

        assertNotNull(u);
    }

    /**
     * Creates the user test.
     */
    @Test
    public void createUserTest() {
        User user = new User("novoUser", "password", "novoUser@Email");
        this.entityManager.getTransaction().begin();
        user = this.usersRepository.saveUser(user);

        this.entityManager.flush();

        final User u = this.usersRepository.getUserbyUsernameJPQL("novoUser");
        final int s = this.usersRepository.getListOfUser().size();

        this.entityManager.getTransaction().rollback();

        assertEquals(5, s);
        assertNotNull(u);

    }

}
