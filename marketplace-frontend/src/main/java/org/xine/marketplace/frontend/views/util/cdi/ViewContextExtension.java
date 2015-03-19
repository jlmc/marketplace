package org.xine.marketplace.frontend.views.util.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.faces.bean.ViewScoped;

/**
 * <p>
 * Copied from Seam Faces 3.1.0.
 * </p>
 * <p>
 * This is a utility class that work was CDI service. It was created to be used in environments with
 * JSF version bellow 2.2.X. where not exists a Annotation ViewScoped manageable by the Context
 * Dependency Injection.
 * </p>
 * <p>
 * As well the classes: <br/>
 * <code>
 * org.xine.masterf.views.util.cdi.ViewContextExtension;
 * org.xine.masterf.views.util.cdi.ViewScopedContext;
 * </code> <br/>
 * Will make the classes with the annotation: <code> @javax.faces.bean.ViewScoped</code> Manageable
 * by CDI.
 * </p>
 * <tt>
 * <b>
 * NOTES:
 *      TO MAKE THIS CLASS WORK WAS CDI SERVICE YOU HAVE TO DO THE FOLLOWING STEPS:
 * </b>
 *      <ol>
 *          <li>create the folder: \src\main\resources\META-INF\services</li>
 *          <li>create in that folder the file: javax.enterprise.inject.spi.Extension</li>
 *          <li>write in that file: org.xine.marketplace.frontend.views.util.cdi.ViewContextExtension</li>
 *      </ol>
 * 
 * 
 * </tt>
 * <hr/>
 * <p>
 * JSF 2.2 also introduces a new CDI scope: javax.faces.view.ViewScoped. Specifying this annotation
 * on a bean binds it with the current view. javax.faces.bean. ViewScoped is targeted for
 * deprecation in a future version, so it is strongly recommended that you use the newly introduced
 * scope.
 * </p>
 * @author Steve Taylor
 */
@SuppressWarnings({"static-method" })
public class ViewContextExtension implements Extension {

    /**
     * Adds the scope.
     * @param event
     *            the event
     */
    public void addScope(@Observes final BeforeBeanDiscovery event) {
        event.addScope(ViewScoped.class, true, true);
    }

    /**
     * Register context.
     * @param event
     *            the event
     */
    public void registerContext(@Observes final AfterBeanDiscovery event) {
        event.addContext(new ViewScopedContext());
    }
}
