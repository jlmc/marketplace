package org.xine.marketplace.frontend.views.util.cdi;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

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
@SuppressWarnings({"static-method", "unchecked", "rawtypes" })
public class ViewScopedContext implements Context, SystemEventListener {

    /** The Constant COMPONENT_MAP_NAME. */
    private final static String COMPONENT_MAP_NAME = "org.jboss.seam.faces.viewscope.componentInstanceMap";

    /** The Constant CREATIONAL_MAP_NAME. */
    private final static String CREATIONAL_MAP_NAME = "org.jboss.seam.faces.viewscope.creationalInstanceMap";

    /** The is jsf subscribed. */
    private boolean isJsfSubscribed = false;

    /*
     * (non-Javadoc)
     * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual)
     */
    @Override
    public <T> T get(final Contextual<T> component) {
        assertActive();
        if (!this.isJsfSubscribed) {
            FacesContext.getCurrentInstance().getApplication()
            .subscribeToEvent(PreDestroyViewMapEvent.class, this);
            this.isJsfSubscribed = true;
        }
        final T instance = (T) getComponentInstanceMap().get(component);
        return instance;
    }

    /*
     * (non-Javadoc)
     * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual,
     * javax.enterprise.context.spi.CreationalContext)
     */
    @Override
    public <T> T get(final Contextual<T> component, final CreationalContext<T> creationalContext) {
        assertActive();
        T instance = get(component);
        if (instance == null) {
            if (creationalContext != null) {
                final Map<Contextual<?>, Object> componentInstanceMap = getComponentInstanceMap();
                final Map<Contextual<?>, CreationalContext<?>> creationalContextMap = getCreationalInstanceMap();
                synchronized (componentInstanceMap) {
                    instance = (T) componentInstanceMap.get(component);
                    if (instance == null) {
                        instance = component.create(creationalContext);
                        if (instance != null) {
                            componentInstanceMap.put(component, instance);
                            creationalContextMap.put(component, creationalContext);
                        }
                    }
                }
            }
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * @see javax.enterprise.context.spi.Context#getScope()
     */
    @Override
    public Class<? extends Annotation> getScope() {
        return ViewScoped.class;
    }

    /*
     * (non-Javadoc)
     * @see javax.enterprise.context.spi.Context#isActive()
     */
    @Override
    public boolean isActive() {
        return getViewRoot() != null;
    }

    /**
     * Assert active.
     */
    private void assertActive() {
        if (!isActive()) {
            throw new ContextNotActiveException(
                    "Seam context with scope annotation @ViewScoped is not active with respect to the current thread");
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.event.SystemEventListener#isListenerForSource(java.lang.Object)
     */
    @Override
    public boolean isListenerForSource(final Object source) {
        if (source instanceof UIViewRoot) {
            return true;
        }
        return false;
    }

    /**
     * We get PreDestroyViewMapEvent events from the JSF servlet and destroy our
     * contextual instances. This should (theoretically!) also get fired if the
     * webapp closes, so there should be no need to manually track all view
     * scopes and destroy them at a shutdown.
     * @param event
     *            the event
     * @see javax.faces.event.SystemEventListener#processEvent(javax.faces.event.SystemEvent)
     */
    @Override
    public void processEvent(final SystemEvent event) {
        if (event instanceof PreDestroyViewMapEvent) {
            final Map<Contextual<?>, Object> componentInstanceMap = getComponentInstanceMap();
            final Map<Contextual<?>, CreationalContext<?>> creationalContextMap = getCreationalInstanceMap();
            if (componentInstanceMap != null) {
                for (final Map.Entry<Contextual<?>, Object> componentEntry : componentInstanceMap
                        .entrySet()) {
                    /*
                     * No way to inform the compiler of type <T> information, so
                     * it has to be abandoned here :(
                     */

                    final Contextual contextual = componentEntry.getKey();
                    final Object instance = componentEntry.getValue();
                    final CreationalContext creational = creationalContextMap.get(contextual);
                    contextual.destroy(instance, creational);
                }
            }
        }
    }

    /**
     * Gets the view root.
     * @return the view root
     */
    protected UIViewRoot getViewRoot() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            return context.getViewRoot();
        }
        return null;
    }

    /**
     * Gets the view map.
     * @return the view map
     */
    protected Map<String, Object> getViewMap() {
        final UIViewRoot viewRoot = getViewRoot();
        if (viewRoot != null) {
            return viewRoot.getViewMap(true);
        }
        return null;
    }

    /**
     * Gets the component instance map.
     * @return the component instance map
     */

    private Map<Contextual<?>, Object> getComponentInstanceMap() {
        final Map<String, Object> viewMap = getViewMap();
        Map<Contextual<?>, Object> map = (ConcurrentHashMap<Contextual<?>, Object>) viewMap
                .get(COMPONENT_MAP_NAME);
        if (map == null) {
            map = new ConcurrentHashMap<Contextual<?>, Object>();
            viewMap.put(COMPONENT_MAP_NAME, map);
        }
        return map;
    }

    /**
     * Gets the creational instance map.
     * @return the creational instance map
     */
    private Map<Contextual<?>, CreationalContext<?>> getCreationalInstanceMap() {
        final Map<String, Object> viewMap = getViewMap();
        Map<Contextual<?>, CreationalContext<?>> map = (Map<Contextual<?>, CreationalContext<?>>) viewMap
                .get(CREATIONAL_MAP_NAME);
        if (map == null) {
            map = new ConcurrentHashMap<Contextual<?>, CreationalContext<?>>();
            viewMap.put(CREATIONAL_MAP_NAME, map);
        }
        return map;
    }

}
