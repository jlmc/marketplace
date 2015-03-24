package org.xine.marketplace.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Client.
 */
@Entity
public class Client {

    /** The id. */
    private Long id;

    /** The name. */
    private String name;

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
     * Gets the client type.
     * @return the client type
     */
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
     * Gets the addresses.
     * @return the addresses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
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
