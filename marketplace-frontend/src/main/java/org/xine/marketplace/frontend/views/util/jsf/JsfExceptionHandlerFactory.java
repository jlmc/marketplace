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
 * To use this class we must configure the JSF, that configuration is done in the
 * <code>face-config.xml</ code> file, this is done through the following code: <br>
 *
 * <pre>
 * {@code
 *   <factory>
 *       <exception-handler-factory>
 *              org.xine.marketplace.frontend.views.util.jsf.JsfExceptionHandlerFactory
 *       </exception-handler-factory>
 * </factory>
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

    /**
     * <p class="changed_added_2_0">
     * Create and return a A new <code>JsfExceptionHandler</code> instance. The implementation must
     * return an <code>JsfExceptionHandler</code> instance suitable for the environment. For
     * example, in some cases it may be desirable for an <code>JsfExceptionHandler</code> to write
     * error information to the response instead of throwing exceptions as in the case of Ajax
     * applications.
     * </p>
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionHandler(this.parent.getExceptionHandler());
    }

}
