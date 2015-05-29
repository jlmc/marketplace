package org.xine.marketplace.model.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class Permission.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The name. */
    private String name;

    /** The desc. */
    private String description;

    /** The users. */
    private Set<User> users = new HashSet<>();

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
     * Gets the name.
     * @return the name
     */
    @Column(name = "name", length = 100, nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the users.
     * @return the users
     */
    @ManyToMany(mappedBy = "permissions")
    public Set<User> getUsers() {
        return this.users;
    }

    /**
     * Sets the users.
     * @param users
     *            the new users
     */
    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    /**
     * Gets the description.
     * @return the description
     */
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
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
        final Permission other = (Permission) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
