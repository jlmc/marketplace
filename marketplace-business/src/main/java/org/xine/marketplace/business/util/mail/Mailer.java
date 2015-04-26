package org.xine.marketplace.business.util.mail;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * The Class Mailer.
 */
@RequestScoped
public class Mailer implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The session config. */
    @Inject
    private SessionConfig sessionConfig;

    /**
     * Creates the mesage.
     * @return the mail message
     */
    public MailMessage createMesage() {
        return new MailMessageImpl(this.sessionConfig);
    }

}
