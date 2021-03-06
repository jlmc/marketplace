package org.xine.marketplace.model.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.xine.marketplace.validator.constraints.Email;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class User.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "user", indexes = {@Index(columnList = "username", unique = true) })
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

    /** The version. Lock Optimistic */
    private Integer version;

    /**
     * Instantiates a new user.
     */
    public User() {}

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
    @Size(min = 4, max = 50)
    @Column(length = 100, unique = false, nullable = false)
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
    @Size(max = 32, min = 4)
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
    @NotNull
    @Size(min = 8, max = 80)
    @Email
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
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

    /**
     * Gets the version.
     * @return the version
     */
    @Version
    @Column(name = "OPTLOCK")
    protected Integer getVersion() {
        return this.version;
    }

    /**
     * Gets the version.
     * @param version
     *            the version
     * @return the version
     */
    protected void setVersion(final Integer v) {
        this.version = v;
    }

}
