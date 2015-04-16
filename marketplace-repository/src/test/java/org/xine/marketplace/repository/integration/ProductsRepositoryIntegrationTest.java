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
@SuppressWarnings({"static-method", "boxing" })
public class ProductsRepositoryIntegrationTest extends AbstractDbUnitJpaTest {

    /**
     * Inits the.
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

    @Test
    public void searchTest_filter_is_empty() {
        final ProductsRepository repository = new ProductsRepository();
        repository.setManager(getEntityManager());
        final ProductFilter filter = new ProductFilter();

        final List<Product> products = repository.search(filter);

        Assert.assertEquals(3, products.size());
    }

    @Test
    public void searchTest_filter_is_null() {
        final ProductsRepository repository = new ProductsRepository();
        repository.setManager(getEntityManager());
        final ProductFilter filter = null;

        final List<Product> products = repository.search(filter);

        Assert.assertEquals(3, products.size());
    }

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

    @Test
    public void searchTest_BY_SKU_AND_NAME() {
        final ProductsRepository repository = new ProductsRepository();
        repository.setManager(getEntityManager());
        final ProductFilter filter = new ProductFilter();

        // sku="23cdef" name="food-name"
        filter.setSku("23cdef");
        filter.setName("food-name");

        final List<Product> products = repository.search(filter);

        Assert.assertEquals(1, products.size());
        Assert.assertEquals("23cdef", products.get(0).getSku());
        Assert.assertEquals("food-name", products.get(0).getName());
    }

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

}