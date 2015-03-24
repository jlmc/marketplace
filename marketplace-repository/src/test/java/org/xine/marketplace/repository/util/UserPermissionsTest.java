package org.xine.marketplace.repository.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;

public class UserPermissionsTest {

	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = JPAUtil.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void createUserWithPermissions() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// //

		User user = new User();
		user.setPassword("passw");
		user.setUsername("createUserWithPermissions");
		user.setEmail("createUserWithPermissions@EMAL");

		Permission permission = new Permission();
		permission.setName("AAA");
		permission.setDescription("createUserWithPermissionsPermi");

		user.getPermissions().add(permission);

		Permission permission2 = new Permission();
		permission2.setName("BBB");
		permission2.setDescription("createUserWithPermissionsPermi2");
		user.getPermissions().add(permission2);

		em.persist(user);

		// //////
		tx.commit();
	}

	@Test
	@Ignore
	public void createUser() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// //

		User user = new User();
		user.setPassword("passw");
		user.setUsername("username-teste");
		user.setEmail("TESTE-USER-1@EMAL");

		em.persist(user);

		// //////
		tx.commit();
	}

}
