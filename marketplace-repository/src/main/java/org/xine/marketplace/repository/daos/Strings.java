package org.xine.marketplace.repository.daos;

/**
 * The Class Strings.
 */
final class Strings {

    /**
     * Checks if is null or blank.
     * @param str
     *            the str
     * @return true, if is null or empty
     */
    public static boolean isNullOrBlank(final String str) {
        return str == null || str.trim().length() == 0;
    }

}
