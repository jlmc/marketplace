package org.xine.marketplace.model.entities;

import org.xine.marketplace.validator.constraints.Email;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class Client.
 */
@Entity
@Table(name = "client")
public class Client {

    /** The id. */
    private Long id;

    /** The name. */
    private String name;

    /** The cnjp. */
    private String cnjp;

    /** The email. */
    private String email;

    /** The client type. */
    private ClientType clientType;

    /** The addresses. */
    private Set<Address> addresses = new HashSet<>();

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
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", length = 100, nullable = false)
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
    @Email
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
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
     * Gets the client type.
     * @return the client type
     */
    @NotNull
    @Column(name = "client_type", length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    public ClientType getClientType() {
        return this.clientType;
    }

    /**
     * Sets the client type.
     * @param clientType
     *            the new client type
     */
    public void setClientType(final ClientType clientType) {
        this.clientType = clientType;
    }

    /**
     * Gets the cnjp.
     * @return the cnjp
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cnjp", length = 50, nullable = false, unique = true)
    public String getCnjp() {
        return this.cnjp;
    }

    /**
     * Sets the cnjp.
     * @param cnjp
     *            the new cnjp
     */
    public void setCnjp(final String cnjp) {
        this.cnjp = cnjp;
    }

    /**
     * Gets the addresses.
     * @return the addresses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    public Set<Address> getAddresses() {
        return this.addresses;
    }

    /**
     * Sets the addresses.
     * @param addresses
     *            the new addresses
     */
    public void setAddresses(final Set<Address> addresses) {
        this.addresses = addresses;
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
        final Client other = (Client) obj;
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
