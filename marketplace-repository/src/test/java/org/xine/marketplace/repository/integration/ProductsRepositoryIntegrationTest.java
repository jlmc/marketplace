package org.xine.marketplace.repository.integration;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.daos.ProductsRepository;
import org.xine.marketplace.repository.filters.ProductFilter;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

/**
 * The Class ProductsIntegrationTest.
 */
public class ProductsRepositoryIntegrationTest extends AbstractDbUnitJpaTest {

	/**
	 * Inits the.
	 *
	 * @throws HibernateException
	 *             the hibernate exception
	 * @throws DatabaseUnitException
	 *             the database unit exception
	 */
	@BeforeClass
	public static void init() throws HibernateException, DatabaseUnitException {
		AbstractDbUnitJpaTest.setDataSetpath("xml/ProductsRepositoryIntegrationTest.xml");
		initEntityManager();
	}

	/**
	 * Gets the product test.
	 *
	 * @return the product test
	 */
	@Test
	public void getProductTest() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		// id="201" sku="20ABC"

		Product product = repository.get(201L);
		Assert.assertNotNull(product);
		Assert.assertEquals("12ABCD", product.getSku());
	}

	@Test
	public void searchTest_filter_is_empty() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		ProductFilter filter = new ProductFilter();

		List<Product> products = repository.search(filter);

		Assert.assertEquals(3, products.size());
	}

	@Test
	public void searchTest_filter_is_null() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		ProductFilter filter = null;

		List<Product> products = repository.search(filter);

		Assert.assertEquals(3, products.size());
	}

	@Test
	public void searchTest_by_name() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		ProductFilter filter = new ProductFilter();
		filter.setName("20ABC");

		List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("20ABC", products.get(0).getName());
	}

	@Test
	public void searchTest_by_SKU() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		ProductFilter filter = new ProductFilter();
		filter.setSku("23BCDE");

		List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23BCDE", products.get(0).getSku());
	}

	@Test
	public void searchTest_BY_SKU_AND_NAME() {
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		ProductFilter filter = new ProductFilter();

		// sku="23cdef" name="food-name"
		filter.setSku("23cdef");
		filter.setName("food-name");

		List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23cdef", products.get(0).getSku());
		Assert.assertEquals("food-name", products.get(0).getName());
	}

	@Test
	@Ignore
	public void saveTest(){
		ProductsRepository repository = new ProductsRepository();
		repository.setManager(entityManager);
		entityManager.getTransaction().begin();
		
		
		Product product = new Product("11food","name-test");
		Category category = new Category();
		// id="6" description="Food" mastercategory="2"
		category.setId(6L);
		category.setDescription("Food");
		product.setCategory(category);
		
		Product savedProduct = repository.save(product);
		
		entityManager.flush();
		
		Assert.assertNotNull(savedProduct);
		//Assert.assertNotNull(savedProduct.getId());
		//System.out.println("product new ID = "+savedProduct.getId());
	}
	
}
