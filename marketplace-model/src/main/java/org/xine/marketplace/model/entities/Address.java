package org.xine.marketplace.model.entities;

import org.xine.marketplace.validator.constraints.ZipCode;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class Address.
 */
@Entity
@Table(name = "client_address")
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

    /** The edit. */
    private boolean edit;

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
    @Size(min = 0, max = 155)
    @Column(name = "street", length = 155, nullable = true)
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
    @Size(max = 8)
    @Column(name = "doorNumber", nullable = true, length = 8)
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
    @NotNull
    @ZipCode
    @Size(min = 7, max = 14)
    @Column(name = "zipCode", nullable = false, length = 15)
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
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "city", length = 50, nullable = false)
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
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "country", length = 50, nullable = false)
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
        return Objects.hash(this.id, String.valueOf(this.country).trim().toUpperCase(), String
                .valueOf(this.city).trim().toUpperCase(), String.valueOf(this.zipCode).trim()
                .toUpperCase(), String.valueOf(this.street).trim().toUpperCase(),
                String.valueOf(this.number).trim().toUpperCase());
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
        if (this.city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!this.city.equals(other.city)) {
            return false;
        }
        if (this.client == null) {
            if (other.client != null) {
                return false;
            }
        } else if (!this.client.equals(other.client)) {
            return false;
        }
        if (this.country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!this.country.equals(other.country)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.number == null) {
            if (other.number != null) {
                return false;
            }
        } else if (!this.number.equals(other.number)) {
            return false;
        }
        if (this.street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!this.street.equals(other.street)) {
            return false;
        }
        if (this.zipCode == null) {
            if (other.zipCode != null) {
                return false;
            }
        } else if (!this.zipCode.equals(other.zipCode)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if is edits the.
     * @return the edit
     */
    @Transient
    public boolean isEdit() {
        return this.edit;
    }

    /**
     * Sets the edits the.
     * @param edit
     *            the edit to set
     */
    public void setEdit(final boolean edit) {
        this.edit = edit;
    }

}
