package org.xine.marketplace.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Address.
 */
@Entity
public class Address implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The street. */
    private String street;

    /** The number. */
    private String number;

    /** The zip code. */
    private String zipCode;

    /** The city. */
    private String city;

    /** The country. */
    private String country;

    /** The client. */
    private Client client;

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
     * Gets the street.
     * @return the street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Sets the street.
     * @param street
     *            the new street
     */
    public void setStreet(final String street) {
        this.street = street;
    }

    /**
     * Gets the number.
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets the number.
     * @param number
     *            the new number
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Gets the zip code.
     * @return the zip code
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets the zip code.
     * @param zipCode
     *            the new zip code
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the city.
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city.
     * @param city
     *            the new city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Gets the country.
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country.
     * @param country
     *            the new country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets the client.
     * @return the client
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return this.client;
    }

    /**
     * Sets the client.
     * @param client
     *            the new client
     */
    public void setClient(final Client client) {
        this.client = client;
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
        final Address other = (Address) obj;
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
