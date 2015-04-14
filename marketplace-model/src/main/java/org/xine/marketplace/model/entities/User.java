package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;



/**
 * The Class User.
 */
@Entity
public class User implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The email. */
    private String email;

    /** The permissions. */
    private Set<Permission> permissions = new HashSet<>();

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     * @param username
     *            the username
     * @param password
     *            the password
     * @param email
     *            the email
     */
    public User(final String username, final String password, final String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

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
     * Gets the username.
     * @return the username
     */
    @NotNull
    @Column(length = 32, unique = true, nullable = false)
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     * @param username
     *            the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    @NotNull
    @Column(length = 32, nullable = false)
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     * @param password
     *            the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     * @return the email
     */
    @Email
    @NotNull
    @Column(length = 128, unique = true)
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
    @ManyToMany(cascade = {CascadeType.ALL })
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "user_id") }, inverseJoinColumns = {@JoinColumn(name = "permission_id") })
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
