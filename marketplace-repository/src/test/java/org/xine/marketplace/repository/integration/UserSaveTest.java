package org.xine.marketplace.repository.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.util.JPAUtil;

import javax.persistence.EntityManager;

@SuppressWarnings({"static-method", "boxing" })
public class UserSaveTest {

    @Test
    @Ignore
    public void test() {
        final EntityManager em = JPAUtil.createEntityManager();
        final UsersRepository ur = new UsersRepository();
        ur.setManager(em);
        em.getTransaction().begin();

        final User user = new User("test", "testPassword", "testEmail");

        ur.saveUser(user);

        em.getTransaction().commit();
    }

    @Test
    @Ignore
    public void testAddWithPermissions() {
        /**
         * saving same object ading same object with him that object will be persiste to
         */
        final EntityManager em = JPAUtil.createEntityManager();
        final UsersRepository ur = new UsersRepository();
        ur.setManager(em);
        em.getTransaction().begin();

        final User user = new User("test2", "testPassword2", "testEmail2");

        final Permission permission = new Permission();
        permission.setName("Admin");
        permission.setDescription("Administration Role");
        user.getPermissions().add(permission);

        ur.saveUser(user);

        em.getTransaction().commit();
    }

    @Test
    @Ignore
    public void test3RemovingTheUser() {
        final User user = new User();
        user.setId(9L);

        final EntityManager em = JPAUtil.createEntityManager();
        final UsersRepository ur = new UsersRepository();
        ur.setManager(em);
        em.getTransaction().begin();

        ur.deletUser(user);

        em.getTransaction().commit();
    }

}
