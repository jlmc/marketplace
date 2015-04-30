package org.xine.marketplace.validator.constraints;

/**
 * The Class StubModel.
 */
public class EmailStubModel {

    /** The email. */
    @Email
    private String email;

    /**
     * Instantiates a new stub model.
     * @param email
     *            the email
     */
    public EmailStubModel(final String email) {
        super();
        this.email = email;
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

}
