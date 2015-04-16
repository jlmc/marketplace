package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Category.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The description. */
    private String description;

    /** The master category. */
    private Category masterCategory;

    /** The sub categories. */
    private Set<Category> subCategories = new HashSet<>();

    /**
     * Gets the id.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the description.
     * @return the description
     */
    @Column(nullable = false, length = 60)
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     * @param description
     *            the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the master category.
     * @return the master category
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "masterCategory")
    public Category getMasterCategory() {
        return this.masterCategory;
    }

    /**
     * Sets the master category.
     * @param masterCategory
     *            the new master category
     */
    public void setMasterCategory(final Category masterCategory) {
        this.masterCategory = masterCategory;
    }

    /**
     * Gets the sub categories.
     * @return the sub categories
     */
    @OneToMany(mappedBy = "masterCategory", cascade = CascadeType.ALL)
    public Set<Category> getSubCategories() {
        return this.subCategories;
    }

    /**
     * Sets the sub categories.
     * @param subCategories
     *            the new sub categories
     */
    public void setSubCategories(final Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
