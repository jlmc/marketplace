package org.xine.marketplace.frontend.views.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * A factory for creating JsfExceptionHandler objects.
 * <p>
 * Encapsulate the default JSF Exception Handler Factory, instantiating
 * <code>JsfExceptionHandler</code>.
 * </p>
 * <p>
 * To use this class we must configure the JSF we we want to use it in the
 * <code> face-config.xml</ code> file, this is done through the following code: <br>
 * *
 *
 * <pre>
 * {@code
 * 
 *   <factory>
 *       <exception-handler-factory>
 *              org.xine.marketplace.frontend.views.util.jsf.JsfExceptionHandlerFactory
 *       </exception-handler-factory>
 * </factory>
 * 
 * }
 * </pre>
 *
 * </p>
 * @see JsfExceptionHandler
 * @see ExceptionHandlerFactory
 * @author joao Costa
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {

    /** The parent. */
    private final ExceptionHandlerFactory parent;

    /**
     * Instantiates a new jsf exception handler factory.
     * @param parent
     *            the parent
     */
    public JsfExceptionHandlerFactory(final ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionHandler(this.parent.getExceptionHandler());
    }

}
