package org.xine.marketplace.frontend.views.controller.locale;

import org.xine.marketplace.frontend.views.controller.locale.event.LocaleChangedEvent;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * The Class ResourceMessage.
 */
@SessionScoped
@Named
public class ResourceMessage implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The bundle. */
    private ResourceBundle bundle;

    /**
     * Gets the message.
     * @param key
     *            the key
     * @param params
     *            the params
     * @return the message
     */
    public String getMessage(final String key, final Object... params) {
        String value = null;
        if (key != null) {
            // value = getResourceBundleByBaseName().getString(key);
            value = getBundle().getString(key);

            if (params != null && params.length > 0) {
                value = MessageFormat.format(value, params);
            }

        }
        return value;
    }

    /**
     * Gets the bundle.
     * @return the bundle
     */
    private ResourceBundle getBundle() {
        if (this.bundle == null) {
            getResourceBundleByVarName();
        }
        return this.bundle;
    }

    /**
     * Locale changed event handler.
     * @param event
     *            the event
     */
    public void localeChangedEventHandler(@Observes final LocaleChangedEvent event) {
        getResourceBundleByVarName();
    }

    /**
     * varName: is the String representing the <var></var> in <resource-bundle>.
     * @return the resource bundle by var name
     */
    private void getResourceBundleByVarName() {
        final FacesContext context = FacesContext.getCurrentInstance();
        // Application app = this.context.getApplication();
        // final ResourceBundle bundle =
        this.bundle = context.getApplication().getResourceBundle(context, "msg");
    }

    /**
     * using baseName: The fully qualified name of the resource bundle (<base-name> in
     * <resource-bundle>).
     * @return the resource bundle by base name
     */
    @SuppressWarnings("unused")
    private static ResourceBundle getResourceBundleByBaseName() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale locale = context.getViewRoot().getLocale();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final ResourceBundle bundle = ResourceBundle.getBundle(
                "org.xine.marketplace.frontend.locale.language", locale, loader);
        return bundle;
    }

}
