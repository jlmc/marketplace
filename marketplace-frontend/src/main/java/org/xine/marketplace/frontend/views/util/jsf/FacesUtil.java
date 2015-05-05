package org.xine.marketplace.frontend.views.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * The Class FacesUtil.
 */
public final class FacesUtil {

    /**
     * Instantiates a new faces util.
     */
    private FacesUtil() {}

    /**
     * Adds the error message.
     * @param message
     *            the message
     */
    public static void addErrorMessage(final String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    /**
     * Adds the error message.
     * @param clientId
     *            the client id
     * @param message
     *            the message
     */
    public static void addErrorMessage(final String clientId, final String message) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    /**
     * Adds the info message.
     * @param message
     *            the message
     */
    public static void addInfoMessage(final String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    /**
     * Checks if current request is postback.
     * The firts request for the resource is not a PostBack, after that, any
     * request over the resource instance is always a Postback.
     * @return true, if is postback
     */
    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    /**
     * Checks if the request is not postback.
     * The firts request for the resource is not a PostBack, after that, any
     * request over the resource instance is always a Postback.
     * @return true, if is not postback
     */
    public static boolean isNotPostback() {
        return !isPostback();
    }
}
