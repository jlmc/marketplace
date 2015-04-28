package org.xine.marketplace.frontend.views.util.helpers;

/**
 * The Class Strings.
 */
public final class Strings {

    /**
     * Instantiates a new strings.
     */
    private Strings() {}

    /**
     * Checks if is null or blank.
     * @param str
     *            the str
     * @return true, if is null or empty
     */
    public static boolean isNullOrBlank(final String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * Checks if is not null or blank.
     * @param str
     *            the str
     * @return true, if is not null or blank
     */
    public static boolean isNotNullOrBlank(final String str) {
        return !isNullOrBlank(str);
    }

}
