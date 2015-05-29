package org.xine.marketplace.repository.daos;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

/**
 * The Class CriteriaHelper.
 */
final class CriteriaHelper {

    /**
     * Instantiates a new helper.
     */
    private CriteriaHelper() {
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

    /**
     * Represents an strategy for matching strings using "like".
     */
    public enum MatchMode {

        /** Match the entire string to the pattern. */
        EXACT {
            @Override
            public String toMatchString(final String pattern) {
                return pattern;
            }
        },

        /** Match the start of the string to the pattern. */
        START {
            @Override
            public String toMatchString(final String pattern) {
                return pattern + '%';
            }
        },

        /** Match the end of the string to the pattern. */
        END {
            @Override
            public String toMatchString(final String pattern) {
                return '%' + pattern;
            }
        },

        /** Match the pattern anywhere in the string. */
        ANYWHERE {
            @Override
            public String toMatchString(final String pattern) {
                return '%' + pattern + '%';
            }
        };

        /**
         * Convert the pattern, by appending/prepending "%".
         * @param pattern
         *            The pattern for convert according to the mode
         * @return The converted pattern
         */
        public abstract String toMatchString(String pattern);
    }

    /**
     * A case-insensitive "like" (similar to Postgres <tt>ilike</tt> operator) using the provided
     * match mode.
     * @param builder
     *            the CriteriaBuilder of the corrent context.
     * @param path
     *            the path {@code Expression<String>} with the property name to use in the
     *            operation.
     * @param pattern
     *            the pattern the pattern to use in the operation.
     * @return the predicate
     */
    public static Predicate ilike(final CriteriaBuilder builder, final Expression<String> path,
            final String pattern) {
        return ilike(builder, path, pattern, MatchMode.EXACT);

    }

    /**
     * A case-insensitive "like" (similar to Postgres <tt>ilike</tt> operator) using the provided
     * match mode.
     * @param builder
     *            the CriteriaBuilder of the corrent context.
     * @param path
     *            the path {@code Expression<String>} with the property name to use in the
     *            operation.
     * @param pattern
     *            the pattern the pattern to use in the operation.
     * @param exact
     *            the exact, by default is used {@code MatchMode.EXACT} .
     * @return the predicate
     */
    public static Predicate ilike(final CriteriaBuilder builder, final Expression<String> path,
            final String pattern, final MatchMode exact) {
        if (pattern == null) {
            throw new IllegalArgumentException("Comparison value passed to ilike cannot be null");
        }
        final MatchMode mode = (exact == null) ? MatchMode.EXACT : exact;

        return builder.like(builder.upper(path), mode.toMatchString(pattern.trim().toUpperCase()));
    }

    /**
     * Builde order by of some expression.
     * @param builder
     *            the builder define the current criteria builder.
     * @param expression
     *            the expression - define the property to order by.
     * @param isDescendent
     *            the is descendent - define if is asc or desc, is desc if true, asc otherwise
     * @return the order
     */
    public static Order orderBy(final CriteriaBuilder builder, final Expression<?> expression,
            final boolean isDescendent) {
        if (isDescendent) {
            return builder.desc(expression);

        }
        return builder.asc(expression);
    }

    /**
     * Builde order by of some expression.
     * @param builder
     *            the builder define the current criteria builder.
     * @param expression
     *            the expression - define the property to order by.
     * @return the order
     */
    public static Order orderBy(final CriteriaBuilder builder, final Expression<?> expression) {
        return orderBy(builder, expression, false);
    }

    /**
     * Cacheable.
     * @param <X>
     *            the generic type
     * @param typedQuery
     *            the typed query
     * @return the typed query
     */
    public static <X> TypedQuery<X> cacheable(final TypedQuery<X> typedQuery) {
        typedQuery.setHint("org.hibernate.cacheable", Boolean.TRUE);
        // typedQuery.unwrap(org.hibernate.Query.class).setCacheable(true)
        return typedQuery;
    }

}
