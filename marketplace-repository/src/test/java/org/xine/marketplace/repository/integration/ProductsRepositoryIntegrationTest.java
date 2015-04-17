package org.xine.marketplace.repository.integration;

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
import org.xine.marketplace.repository.hdaos.HProducts;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

import java.util.List;

/**
 * The Class ProductsIntegrationTest.
 */
@SuppressWarnings({ "static-method", "boxing" })
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
		AbstractDbUnitJpaTest
				.setDataSetpath("xml/ProductsRepositoryIntegrationTest.xml");
		initEntityManager();
	}

	/**
	 * Gets the product test.
	 * 
	 * @return the product test
	 */
	@Test
	public void getProductTest() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		// id="201" sku="20ABC"

		final Product product = repository.get(201L);
		Assert.assertNotNull(product);
		Assert.assertEquals("12ABCD", product.getSku());
	}

	/**
	 * Search test_filter_is_empty.
	 */
	@Test
	public void searchTest_filter_is_empty() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		final ProductFilter filter = new ProductFilter();

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(3, products.size());
	}

	/**
	 * Search test_filter_is_null.
	 */
	@Test
	public void searchTest_filter_is_null() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		final ProductFilter filter = null;

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(3, products.size());
	}

	/**
	 * Search test_by_name.
	 */
	@Test
	public void searchTest_by_name() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		final ProductFilter filter = new ProductFilter();
		filter.setName("20ABC");

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("20ABC", products.get(0).getName());
	}

	/**
	 * Search test_by_ sku.
	 */
	@Test
	public void searchTest_by_SKU() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		final ProductFilter filter = new ProductFilter();
		filter.setSku("23BCDE");

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23BCDE", products.get(0).getSku());
	}

	/**
	 * Search test_ b y_ sk u_ an d_ name.
	 */
	@Test
	public void searchTest_BY_SKU_AND_NAME() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		final ProductFilter filter = new ProductFilter();

		// sku="23CDEF" name="food-name"
		filter.setSku("23CDEF");
		filter.setName("food-name");

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23CDEF", products.get(0).getSku());
		Assert.assertEquals("food-name", products.get(0).getName());
	}

	/**
	 * Gets the by sku test.
	 * 
	 * @return the by sku test
	 */
	@Test
	public void getBySKUTest() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());

		final String sku = "12ABCD";

		Product products = repository.getBySKU(sku);

		Assert.assertNotNull(products);
		Assert.assertEquals("20ABC", products.getName());
	}
	
	@Test
	public void getBySKUNullTest() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		
		final String sku = "as987384";
		
		Product products = repository.getBySKU(sku);
		
		Assert.assertNull(products);
	}

	/**
	 * Gets the by sku test.
	 * 
	 * @return the by sku test
	 */
	@Test
	// @Ignore
	public void getHpro() {
		final HProducts repository = new HProducts();
		repository.setManager(getEntityManager());

		// final String sku = "23CDEF";

		final ProductFilter filter = new ProductFilter();
		// sku="23CDEF" name="food-name"
		filter.setSku("23CDEF");
		filter.setName("food-name");

		final List<Product> products = repository.search(filter);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23CDEF", products.get(0).getSku());
		Assert.assertEquals("food-name", products.get(0).getName());

	}

	/**
	 * Gets the by sku test.
	 * 
	 * @return the by sku test
	 */
	@Test
	// @Ignore
	public void getHBySKU() {
		final HProducts repository = new HProducts();
		repository.setManager(getEntityManager());

		final String sku = "23CDEF";

		final List<Product> products = repository.getBySKU(sku);

		Assert.assertEquals(1, products.size());
		Assert.assertEquals("23CDEF", products.get(0).getSku());
		Assert.assertEquals("food-name", products.get(0).getName());

	}

	/**
	 * Save test.
	 */
	@Test
	@Ignore
	public void saveTest() {
		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		getEntityManager().getTransaction().begin();

		final Product product = new Product("11food", "name-test");
		final Category category = new Category();
		// id="6" description="Food" mastercategory="2"
		category.setId(6L);
		category.setDescription("Food");
		product.setCategory(category);

		final Product savedProduct = repository.save(product);

		getEntityManager().flush();

		Assert.assertNotNull(savedProduct);
	}

	/**
	 * Gets the productby id with category test.
	 *
	 * @return the productby id with category test
	 */
	@Test
	public void getProductbyIdWithCategoryTest() {
		Long myID = Long.valueOf(201L);

		final ProductsRepository repository = new ProductsRepository();
		repository.setManager(getEntityManager());
		Product product = repository.getById(myID);

		Assert.assertNotNull(product);
		Assert.assertNotNull(product.getCategory());

		System.out.println("ID C =" + product.getCategory().getDescription());
		System.out.println("ID C MASTER="
				+ product.getCategory().getMasterCategory().getDescription());
	}

}
