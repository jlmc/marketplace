package org.xine.marketplace.frontend.views.exer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Contract.
 */
public class Contract implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The social reson. */
    private String socialReson;

    /** The zip code. */
    private String zipCode;

    /** The address. */
    private String address;

    /** The modalidade. */
    private String modalidade;

    /** The in date. */
    private Date inDate;

    /** The value. */
    private String value;

    /** The m payment. */
    private String mPayment;

    /** The payment. */
    private String fPayment;

    /**
     * Instantiates a new contract.
     */
    public Contract() {
    }

    /**
     * Instantiates a new contract.
     * @param socialReson
     *            the social reson
     * @param zipCode
     *            the zip code
     * @param address
     *            the address
     * @param modalidade
     *            the modalidade
     * @param inDate
     *            the in date
     * @param value
     *            the value
     * @param mPayment
     *            the m payment
     * @param fPayment
     *            the f payment
     */
    public Contract(final String socialReson, final String zipCode, final String address,
            final String modalidade, final Date inDate, final String value, final String mPayment,
            final String fPayment) {
        super();
        this.socialReson = socialReson;
        this.zipCode = zipCode;
        this.address = address;
        this.modalidade = modalidade;
        this.inDate = inDate;
        this.value = value;
        this.mPayment = mPayment;
        this.fPayment = fPayment;
    }

    /**
     * Gets the id.
     * @return the id
     */
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
     * Gets the social reson.
     * @return the social reson
     */
    public String getSocialReson() {
        return this.socialReson;
    }

    /**
     * Sets the social reson.
     * @param socialReson
     *            the new social reson
     */
    public void setSocialReson(final String socialReson) {
        this.socialReson = socialReson;
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
     * Gets the address.
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address.
     * @param address
     *            the new address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Gets the modalidade.
     * @return the modalidade
     */
    public String getModalidade() {
        return this.modalidade;
    }

    /**
     * Sets the modalidade.
     * @param modalidade
     *            the new modalidade
     */
    public void setModalidade(final String modalidade) {
        this.modalidade = modalidade;
    }

    /**
     * Gets the in date.
     * @return the in date
     */
    public Date getInDate() {
        return this.inDate;
    }

    /**
     * Sets the in date.
     * @param inDate
     *            the new in date
     */
    public void setInDate(final Date inDate) {
        this.inDate = inDate;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     * @param value
     *            the new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Gets the m payment.
     * @return the m payment
     */
    public String getmPayment() {
        return this.mPayment;
    }

    /**
     * Sets the m payment.
     * @param mPayment
     *            the new m payment
     */
    public void setmPayment(final String mPayment) {
        this.mPayment = mPayment;
    }

    /**
     * Gets the f payment.
     * @return the f payment
     */
    public String getfPayment() {
        return this.fPayment;
    }

    /**
     * Sets the f payment.
     * @param fPayment
     *            the new f payment
     */
    public void setfPayment(final String fPayment) {
        this.fPayment = fPayment;
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
        final Contract other = (Contract) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Contract [id=" + this.id + ", socialReson=" + this.socialReson + ", zipCode="
                + this.zipCode + ", address=" + this.address + ", modalidade=" + this.modalidade
                + ", inDate=" + this.inDate + ", value=" + this.value + ", mPayment="
                + this.mPayment + ", fPayment=" + this.fPayment + "]";
    }

}
