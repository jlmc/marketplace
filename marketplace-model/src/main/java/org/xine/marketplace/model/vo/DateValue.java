package org.xine.marketplace.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class DateValue.
 */
public class DateValue implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The date. */
    private Date date;

    /** The value. */
    private BigDecimal value;

    /**
     * Instantiates a new date value.
     * @param date
     *            the date
     * @param value
     *            the value
     */
    public DateValue(final Date date, final BigDecimal value) {
        super();
        this.date = date;
        this.value = value;
    }

    /**
     * Gets the date.
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets the date.
     * @param date
     *            the date to set
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Gets the value.
     * @return the value
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     * @param value
     *            the value to set
     */
    public void setValue(final BigDecimal value) {
        this.value = value;
    }

}
