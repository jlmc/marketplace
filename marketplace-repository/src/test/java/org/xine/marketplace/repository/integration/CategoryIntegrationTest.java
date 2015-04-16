package org.xine.marketplace.repository.integration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dbunit.DatabaseUnitException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.repository.integration.helper.AbstractDbUnitJpaTest;

/**
 * The Class CategoryIntegrationTest.
 */
public class CategoryIntegrationTest extends AbstractDbUnitJpaTest {

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
		AbstractDbUnitJpaTest.setDataSetpath("xml/Category.xml");
		initEntityManager();
	}

	/**
	 * select category0_.id as id1_3_0_, category0_.description as
	 * descript2_3_0_, category0_.masterCategory as masterCa3_3_0_ 
	 * from category category0_ 
	 * where category0_.id=?.
	 */
	@Test
	public void testFind() {
		Category user = entityManager.find(Category.class, 1L);
		Assert.assertNotNull(user);
		// Assert.assertEquals("userTest", user.getName());
	}

	/**
	 * Gets the child of master category.
	 *
	 * @return the child of master category
	 */
	@SuppressWarnings({"unchecked", "unused", "rawtypes"})
	@Test
	public void getChildOfMasterCategory() {
		Category c = new Category();
		c.setId(1L);

		EntityManager manager = entityManager;
		// EntityManager manager = JPAUtil.createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Category> cq = builder.createQuery(Category.class);
		Root<Category> root = cq.from(Category.class);

		// final Join<Message, Ticket> ticket = (Join) message.fetch("ticket");
		Join<Category, Category> join = (Join) root.fetch("masterCategory");

		cq.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		Expression<Category> master = builder.parameter(Category.class,
				"CATEGORY");
		predicates.add(builder.equal(root.get("masterCategory"), master));

		cq.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Category> query = manager.createQuery(cq);

		query.setParameter("CATEGORY", c);

		List<Category> subCategories = query.getResultList();

		Assert.assertEquals("should have 4 registers", 4, subCategories.size());
	}

	/**
	 * Gets the master category.
	 *
	 * @return the master category
	 */
	@Test
	public void getMasterCategory() {
		EntityManager manager = entityManager;

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Category> cq = builder.createQuery(Category.class);

		Root<Category> categorysRoot = cq.from(Category.class);
		cq.select(categorysRoot);
		cq.where(builder.isNull(categorysRoot.get("masterCategory")));
		// cq.where(builder.isNull(categorysRoot.get("masterCategory")));

		// EXECUTE
		// get the query to execute
		final TypedQuery<Category> query = manager.createQuery(cq);
		// execution the query
		final List<Category> categorys = query.getResultList();

		Assert.assertEquals("should have 4 registers", 4, categorys.size());
	}

}
