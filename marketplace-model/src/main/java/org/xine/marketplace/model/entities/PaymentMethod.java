package org.xine.marketplace.model.entities;

/**
 * The Enum PaymentMethod.
 */
public enum PaymentMethod {

    /** The cash. with money */
    CASH("Cash"),

    /** The credit card. */
    CREDIT_CARD("Credit card"),

    /** The debit card. */
    DEBIT_CARD("Debit card"),

    /** The cheque. */
    CHEQUE("Cheque"),

    /** The paypal. */
    PAYPAL("Paypal");

    /** The description. */
    private String description;

    /**
     * Instantiates a new payment method.
     * @param description
     *            the description
     */
    private PaymentMethod(final String description) {
        this.description = description;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

}
