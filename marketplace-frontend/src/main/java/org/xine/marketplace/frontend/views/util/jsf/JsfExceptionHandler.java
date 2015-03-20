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

public class JsfExceptionHandler extends ExceptionHandlerWrapper{


	private ExceptionHandler wrapped;

	public JsfExceptionHandler(ExceptionHandler wrapper){
		this.wrapped = wrapper;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException{
		//all exception events on the stake pool
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		boolean remove = false;

		while(events.hasNext()){
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// the throw exception
			Throwable exceptionThrows = context.getException();
			try{
				if(exceptionThrows instanceof ViewExpiredException){
					remove = true;
					redirect("/");
				}
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

	private void redirect(String page) {
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			// - /SalesOrders
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			// resposta esta completa, 
			//evitar que outro processamento jsf do seu ciclo de vida seja feito
			facesContext.responseComplete();
		}catch(IOException e){
			throw new FacesException(e);
		}

	}






}
