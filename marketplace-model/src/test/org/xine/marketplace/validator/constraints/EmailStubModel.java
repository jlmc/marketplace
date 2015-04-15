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
	 *
	 * @param email the email
	 */
	public EmailStubModel(String email) {
		super();
		this.email = email;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
