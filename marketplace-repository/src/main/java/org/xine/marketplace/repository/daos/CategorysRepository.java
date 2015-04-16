package org.xine.marketplace.repository.daos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.xine.marketplace.model.entities.Category;

/**
 * The Class CategorysRepository.
 */
public class CategorysRepository {
	
	/** The manager. */
	@Inject
	private EntityManager manager;
	
	/**
	 * Sets the manager.
	 *
	 * @param manager the new manager
	 */
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * Gets the master.
	 *
	 * @return the master
	 */
	public List<Category> getRootCategorys(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Category> cq = builder.createQuery(Category.class);

		Root<Category> categorysRoot = cq.from(Category.class);
		cq.select(categorysRoot);
		cq.where(builder.isNull(categorysRoot.get("masterCategory")));

		// EXECUTE -  get the query to execute
		final TypedQuery<Category> query = manager.createQuery(cq);
		final List<Category> categorys = query.getResultList();
		
		return categorys;
	}
	
	/**
	 * Gets the child category.
	 *
	 * @param father the father
	 * @return the child category
	 */
	public List<Category> getChildCategorys(Category father){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Category> cq = builder.createQuery(Category.class);
		Root<Category> root = cq.from(Category.class);

		// final Join<Message, Ticket> ticket = (Join) message.fetch("ticket");
		//Join<Category, Category> join = (Join) =
		root.fetch("masterCategory");
		cq.select(root);

		final List<Predicate> predicates = new ArrayList<>();
		Expression<Category> master = builder.parameter(Category.class,"CATEGORY");
		predicates.add(builder.equal(root.get("masterCategory"), master));
		cq.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Category> query = manager.createQuery(cq);

		query.setParameter("CATEGORY", father);
		List<Category> subCategories = query.getResultList();
		return subCategories;
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Category getById(Long id) {
		return this.manager.find(Category.class, id);
	}

}
