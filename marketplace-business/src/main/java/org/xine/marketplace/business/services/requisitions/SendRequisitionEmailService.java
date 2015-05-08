package org.xine.marketplace.business.services.requisitions;

import org.apache.velocity.tools.generic.NumberTool;
import org.xine.email.api.MailMessage;
import org.xine.email.impl.templating.velocity.VelocityTemplate;
import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.business.util.mail.Mailer;
import org.xine.marketplace.model.entities.Requisition;

import java.util.Locale;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * The Class SendRequisitionEmailService.
 */
@Default
public class SendRequisitionEmailService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    /**
     * Send.
     * @param requisition
     *            the requisition
     */
    public void send(final Requisition requisition) {
        try {
            final MailMessage message = this.mailer
                    .createMesage()
                    .to(requisition.getClient().getEmail())
                    .subject("Requisition Details - " + requisition.getId())
                    .bodyHtml(
                            new VelocityTemplate(getClass().getResourceAsStream(
                                    "/emails/requisitionDetails.template")))
                    .put("req", requisition).put("numberTool", new NumberTool())
                    .put("locale", new Locale("pt", "PT"));

            message.send();
        } catch (final Exception e) {
            throw new BusinessException("Error sending the email of the Requisition "
                    + requisition.getId() + " to the Client.");
        }
    }
}
