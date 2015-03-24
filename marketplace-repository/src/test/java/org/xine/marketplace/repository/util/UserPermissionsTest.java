package org.xine.marketplace.repository.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.dbunit.dataset.common.handlers.Helper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class UserPermissionsTest {

	private EntityManager em;
	
	//private JIntegrity helper = new JIntegrity();
	
	private IntegrationHelper helper = new IntegrationHelper("marketplace-repository");

	@Before
	public void init() throws Exception {
		//helper = new JIntegrity().path("xml").xml("User").insert();
		//em = JPAUtil.createEntityManager();
		
		//helper.cleanAndInsert();
		//em = JPAHelper.currentEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		//em.close();
		//helper.clean();
		this.helper.closeEntityManager();
	}

	@Test
	@Ignore
	public void createUserWithPermissions() {
		em = JPAHelper.currentEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
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
		//tx.commit();
	}
	
	@Test
	public void getUser(){
		
		this.helper.init();
		this.helper.cleanDB();
		
		em = this.helper.currentEntityManager();
		
		List<User> users = em.createQuery("from User").getResultList();
		
		//User user = em.find(User.class, Long.valueOf(1010));
		//System.out.println(user.getUsername() + " - "+ user.getId());
		Assert.assertNotNull(users);
		
		
	}

	@Test
	@Ignore
	public void createUser() {
		EntityTransaction tx = JPAUtil.createEntityManager().getTransaction();
		tx.begin();
		// //

		
		//em.persist(user);

		// //////
		tx.commit();
	}

}
