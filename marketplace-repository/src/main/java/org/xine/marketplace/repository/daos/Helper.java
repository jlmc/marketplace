package org.xine.marketplace.repository.daos;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * The Class Helper.
 */
final class Helper {

    /**
     * Instantiates a new helper.
     */
    private Helper() {
        //
    }

    /**
     * use this method when you need to get a SingleResult, but you don't have sure
     * that exist and you don't want handler the {@code NoResultException}.
     * Uncheck the Throws of NoResultException.
     * @param <T>
     *            the generic type
     * @param typedQuery
     *            the typed query
     * @return the t - instance of T if exists, {@code null} otherwise.
     */
    public static <T> T getSingleResultUncheck(final TypedQuery<T> typedQuery) {
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

}
