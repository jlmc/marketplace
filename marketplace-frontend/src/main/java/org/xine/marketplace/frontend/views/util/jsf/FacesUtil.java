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
	private FacesUtil(){}
	
	/**
	 * Adds the error message.
	 *
	 * @param message the message
	 */
	public static void addErrorMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message, message));
	}
}
