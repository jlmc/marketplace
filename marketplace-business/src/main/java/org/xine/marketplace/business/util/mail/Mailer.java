package org.xine.marketplace.business.util.mail;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

/**
 * The Class Mailer.
 */
@RequestScoped
public class Mailer implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SessionConfig sessionConfig;
	
	public MailMessage createMesage(){
		return new MailMessageImpl(sessionConfig);
	}

}
