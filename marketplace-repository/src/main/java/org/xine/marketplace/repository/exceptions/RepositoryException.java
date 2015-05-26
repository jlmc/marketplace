package org.xine.marketplace.repository.exceptions;

/**
 * The Class RepositoryException.
 */
public class RepositoryException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The Enum Type.
     */
    public enum Type {

        /** The default. */
        DEFAULT,
        /** The concurrence. */
        CONCURRENCE
    }

    /** The type. */
    private final Type type;

    /**
     * Instantiates a new repository exception.
     * @param msg
     *            the msg
     */
    public RepositoryException(final String msg) {
        super(msg);
        this.type = Type.DEFAULT;
    }

    /**
     * Respository exception.
     * @param msg
     *            the msg
     * @param throwable
     *            the throwable
     */
    public RepositoryException(final String msg, final Throwable throwable) {
        super(msg, throwable);
        this.type = Type.DEFAULT;
    }

    /**
     * Respository exception.
     * @param msg
     *            the msg
     * @param throwable
     *            the throwable
     * @param type
     *            the type
     */
    public RepositoryException(final String msg, final Throwable throwable, final Type type) {
        super(msg, throwable);
        this.type = type;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

}
