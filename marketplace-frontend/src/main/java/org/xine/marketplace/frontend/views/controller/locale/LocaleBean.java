package org.xine.marketplace.frontend.views.controller.locale;

import org.xine.marketplace.frontend.views.controller.locale.event.LocaleChangedEvent;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class LocaleBean.
 */
@SessionScoped
@Named
public class LocaleBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The faces context. */
    @Inject
    private FacesContext facesContext;

    /** The current locale. */
    private Locale currentLocale = Locale.ENGLISH;

    /** The requisition changed event. */
    @Inject
    private Event<LocaleChangedEvent> localeChangeEvent;

    @Produces
    @RequestScoped
    public Locale getLocale() {
        return this.currentLocale;
    }

    /**
     * English locale.
     */
    public void englishLocale() {
        this.currentLocale = Locale.ENGLISH;
        this.facesContext.getViewRoot().setLocale(Locale.ENGLISH);
        this.localeChangeEvent.fire(new LocaleChangedEvent(this.currentLocale));
    }

    /**
     * Portuguese locale.
     */
    public void portugueseLocale() {
        final UIViewRoot viewRoot = this.facesContext.getViewRoot();
        this.currentLocale = new Locale("pt");
        viewRoot.setLocale(this.currentLocale);
        this.localeChangeEvent.fire(new LocaleChangedEvent(this.currentLocale));
    }

    /**
     * Gets the current locale.
     * @return the current locale
     */
    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

}
