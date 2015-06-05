package org.xine.marketplace.frontend.views.controller.locale.event;

import java.util.Locale;

/**
 * The Class LocaleChangedEvent.
 */
public class LocaleChangedEvent {

    /** The locale. */
    private final Locale locale;

    /**
     * Instantiates a new locale changed event.
     * @param locale
     *            the locale
     */
    public LocaleChangedEvent(final Locale locale) {
        super();
        this.locale = locale;
    }

    /**
     * Gets the locale.
     * @return the locale
     */
    public Locale getLocale() {
        return this.locale;
    }

}
