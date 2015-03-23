package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Class Category.
 */
public class Category implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The desc. */
	private String desc;
	
	/** The master category. */
	private Category masterCategory;
	
	/** The sub categories. */
	private Set<Category> subCategories = new HashSet<>();

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the master category.
	 *
	 * @return the master category
	 */
	public Category getMasterCategory() {
		return masterCategory;
	}

	/**
	 * Sets the master category.
	 *
	 * @param masterCategory the new master category
	 */
	public void setMasterCategory(Category masterCategory) {
		this.masterCategory = masterCategory;
	}

	/**
	 * Gets the sub categories.
	 *
	 * @return the sub categories
	 */
	public Set<Category> getSubCategories() {
		return subCategories;
	}

	/**
	 * Sets the sub categories.
	 *
	 * @param subCategories the new sub categories
	 */
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
