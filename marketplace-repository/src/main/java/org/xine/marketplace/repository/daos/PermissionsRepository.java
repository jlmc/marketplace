package org.xine.marketplace.repository.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.xine.marketplace.model.entities.Permission;

/**
 * The Class PermissionsRepository.
 */
public class PermissionsRepository implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The entity manager. */
    @Inject
    private EntityManager entityManager;

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public List<Permission> getPermissions() {
	final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
	final CriteriaQuery<Permission> criteriaQuery = builder
		.createQuery(Permission.class);
	final Root<Permission> root = criteriaQuery.from(Permission.class);

	criteriaQuery.select(root);

	criteriaQuery.orderBy(builder.asc(builder.upper(root.get("name"))));

	final TypedQuery<Permission> typedQuery = this.entityManager
		.createQuery(criteriaQuery);
	return typedQuery.getResultList();
    }

    /**
     * Gets the permission by id.
     *
     * @param id
     *            the id
     * @return the permission by id
     */
    public Permission getPermissionById(final Long id) {
	return this.entityManager.find(Permission.class, id);
    }

    /**
     * Sets the entity manager.
     *
     * @param entityManager
     *            the new entity manager
     */
    public void setEntityManager(final EntityManager entityManager) {
	this.entityManager = entityManager;
    }
}
