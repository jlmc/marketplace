package org.xine.marketplace.frontend.views.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * A factory for creating JsfExceptionHandler objects.
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory{

	/** The parent. */
	private ExceptionHandlerFactory parent;
	
	/**
	 * Instantiates a new jsf exception handler factory.
	 *
	 * @param parent the parent
	 */
	public JsfExceptionHandlerFactory(ExceptionHandlerFactory parent){
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JsfExceptionHandler(parent.getExceptionHandler());
	}

}
