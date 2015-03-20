package org.xine.marketplace.frontend.views.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.xine.marketplace.business.BusinessException;

/**
 * The Class JsfExceptionHandler.
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper{


	/** The wrapped. */
	private ExceptionHandler wrapped;

	/**
	 * Instantiates a new jsf exception handler.
	 *
	 * @param wrapper the wrapper
	 */
	public JsfExceptionHandler(ExceptionHandler wrapper){
		this.wrapped = wrapper;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException{
		//all exception events on the Stack Pool
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		boolean remove = false;

		while(events.hasNext()){
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// the throws exception
			Throwable exceptionThrows = context.getException();
			
			BusinessException businessException = this.getBusinessException(exceptionThrows);
			
			try{
				if(exceptionThrows instanceof ViewExpiredException){
					remove = true;
					redirect("/");
				}
				else if(businessException != null){
					remove = true;
					FacesUtil.addErrorMessage(businessException.getMessage());
				}else{
					remove = true;
					redirect("/Error.xhtml");
				}
				//TODO:: we goes to here others exeptions types that we want to treat
			}
			finally{
				//we just want to remove the handled exception
				if(remove){
					events.remove();
				}
			}
		}

		getWrapped().handle();

	}

	/**
	 * Gets the business exception.
	 *
	 * @param exception the exception
	 * @return the business exception
	 */
	private BusinessException getBusinessException(Throwable exception) {
		if(exception instanceof BusinessException){
			return (BusinessException)exception;
		}else if(exception.getCause() != null){
			return this.getBusinessException(exception.getCause());
		}
		return null;
	}

	/**
	 * Redirect.
	 *
	 * @param page the page
	 */
	private void redirect(String page) {
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			// - /SalesOrders
			// this will be the finalname defined for the application
			// "/"+ finalName  
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			
			// at this time the response is complete.
			// we want to prevent onother processing of JSF life cycle, so we goes 
			// the faceContext as responseComplete
			
			facesContext.responseComplete();
		}catch(IOException e){
			throw new FacesException(e);
		}

	}






}
