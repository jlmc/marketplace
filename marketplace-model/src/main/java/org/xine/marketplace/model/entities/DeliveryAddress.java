package org.xine.marketplace.model.entities;

import org.xine.marketplace.validator.constraints.ZipCode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class DeliveryAddress.
 */
@Embeddable
public class DeliveryAddress implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

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

    /** The delivery name. */
    private String deliveryName;

    /**
     * Instantiates a new delivery address.
     */
    public DeliveryAddress() {}

    /**
     * Instantiates a new delivery address.
     * @param street
     *            the street
     * @param number
     *            the number
     * @param zipCode
     *            the zip code
     * @param city
     *            the city
     * @param country
     *            the country
     * @param deliveryName
     *            the delivery name
     */
    public DeliveryAddress(final String street, final String number, final String zipCode,
            final String city, final String country, final String deliveryName) {
        super();
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.deliveryName = deliveryName;
    }

    /**
     * Gets the street.
     * @return the street
     */
    @Size(max = 150)
    @Column(name = "delivery_street", length = 200, nullable = true)
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
    @Size(max = 10)
    @Column(name = "delivery_number_door", length = 15, nullable = true)
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
    @Size(max = 15)
    @Column(name = "delivery_zip_code", length = 15, nullable = false)
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
    @Size(max = 100)
    @Column(name = "delivery_city", length = 100, nullable = false)
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
    @Size(max = 100)
    @Column(name = "delivery_country", length = 100, nullable = false)
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
     * Gets the delivery name.
     * @return the delivery name
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "delivery_name", length = 200, nullable = false)
    public String getDeliveryName() {
        return this.deliveryName;
    }

    /**
     * Sets the delivery name.
     * @param deliveryName
     *            the new delivery name
     */
    public void setDeliveryName(final String deliveryName) {
        this.deliveryName = deliveryName;
    }
}
