package org.xine.marketplace.repository.daos;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Product;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class ProductsIntegrationTest {

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
	public void getProductById(){
		// given
		EntityManager manager = JPAHelper.currentEntityManager();
		Product p = manager.find(Product.class, Long.valueOf(1L));
		
		Assert.assertNotNull("find product by ID should not be null",p);
	}

}
