package org.xine.marketplace.frontend.views.util.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xine.marketplace.business.BusinessException;

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

/**
 * The Class JsfExceptionHandler.
 * <p>
 * Jsf Exception Handler is the handler application of exceptions. Exception is
 * ViewExpiredException, except for the treatment of this very important when using AJAX many
 * requests. We could think of to define an error page, as with other web solutions with servlets
 * and JSP, but such solution does not work for AJAX requests, and the user would not have feedback
 * with error.
 * </p>
 * <p>
 * The JSF already has a handler plays, then the strategy is to encapsulate this handler and treat
 * executions we want, the ones we do not want handle we delegate to the JSF handler.
 * </p>
 * @see ExceptionHandlerWrapper
 * @see JsfExceptionHandlerFactory
 * @author Joao Costa
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private static final Log LOG = LogFactory.getLog(JsfExceptionHandler.class);

    /** The wrapped. */
    private final ExceptionHandler wrapped;

    /**
     * Instantiates a new jsf exception handler.
     * @param wrapper
     *            the wrapper
     */
    public JsfExceptionHandler(final ExceptionHandler wrapper) {
        this.wrapped = wrapper;
    }

    /**
     * @return the wrapped {@link ExceptionHandler} instance
     * @see javax.faces.FacesWrapper#getWrapped()
     */
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    /**
     * <p>
     * Handler the Exception types that matters handler, the others delegates for the default
     * behavior of {@code ExceptionHandlerWrapper#handle()}
     * </p>
     * <p>
     * The default behavior of this method is to call
     * {@link javax.faces.context.ExceptionHandler#handle()} on the wrapped {@link ExceptionHandler}
     * object.
     * </p>
     * @see javax.faces.context.ExceptionHandler#handle()
     */
    @Override
    public void handle() throws FacesException {
        // all exception events on the Stack Pool
        final Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
                .iterator();

        boolean remove = false;

        while (events.hasNext()) {
            final ExceptionQueuedEvent event = events.next();
            final ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
                    .getSource();

            // the throws exception
            final Throwable exceptionThrows = context.getException();

            final BusinessException businessException = getBusinessException(exceptionThrows);

            try {
                if (exceptionThrows instanceof ViewExpiredException) {
                    remove = true;
                    redirect("/");
                } else if (businessException != null) {
                    remove = true;
                    FacesUtil.addErrorMessage(businessException.getMessage());
                } else {
                    remove = true;
                    LOG.error("SYSTEM ERROR: " + exceptionThrows.getMessage(), exceptionThrows);
                    redirect("/Error.xhtml");
                }
                // TODO:: we goes to here others exeptions types that we want to treat
            } finally {
                // we just want to remove the handled exception
                if (remove) {
                    events.remove();
                }
            }
        }

        getWrapped().handle();

    }

    /**
     * Gets the business exception.
     * @param exception
     *            the exception
     * @return the business exception
     */
    private BusinessException getBusinessException(final Throwable exception) {
        if (exception instanceof BusinessException) {
            return (BusinessException) exception;
        } else if (exception.getCause() != null) {
            return getBusinessException(exception.getCause());
        }
        return null;
    }

    /**
     * Redirect.
     * @param page
     *            the page
     */
    private static void redirect(final String page) {
        try {
            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final ExternalContext externalContext = facesContext.getExternalContext();
            // - /SalesOrders
            // this will be the finalname defined for the application
            // "/"+ finalName
            final String contextPath = externalContext.getRequestContextPath();

            externalContext.redirect(contextPath + page);

            // at this time the response is complete.
            // we want to prevent onother processing of JSF life cycle, so we goes
            // the faceContext as responseComplete

            facesContext.responseComplete();
        } catch (final IOException e) {
            throw new FacesException(e);
        }

    }

}
