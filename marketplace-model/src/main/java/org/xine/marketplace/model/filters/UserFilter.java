package org.xine.marketplace.model.filters;

import org.xine.marketplace.model.entities.Permission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class UserFilter.
 */
public class UserFilter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The name. */
    private String name;

    /** The email. */
    private String email;

    /** The permissions. */
    private Set<Permission> permissions = new HashSet<>();

    /**
     * Instantiates a new user filter.
     */
    public UserFilter() {
        super();
    }

    /**
     * Instantiates a new user filter.
     * @param name
     *            the name
     * @param email
     *            the email
     */
    public UserFilter(final String name, final String email) {
        super();
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the name.
     * @return the name
     */
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
     * Gets the email.
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email.
     * @param email
     *            the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the permissions.
     * @return the permissions
     */
    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * Sets the permissions.
     * @param permissions
     *            the new permissions
     */
    public void setPermissions(final Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
