package org.xine.marketplace.repository.daos;


//import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.assertNotSame;
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.User;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class IntegrationUsersTestOne {
	
	static JIntegrity helper;
	
	@BeforeClass
	public static void beforeClass() {
		// this order is very important because we are using
		// the 
		JPAHelper.entityManagerFactory("default");

		helper = new JIntegrity();
		helper.cleanAndInsert();
	}

	@Test
	public void test(){
		// given
		EntityManager manager = JPAHelper.currentEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		
		final Root<User> root = cq.from(User.class);
		cq.select(root);
		
		TypedQuery<User> query = manager.createQuery(cq);
		
		List<User> users = query.getResultList();
		
		Assert.assertEquals("should have 4 registers", 4, users.size());
		
		//assertEquals("should have 4 registers", );
	}

}
