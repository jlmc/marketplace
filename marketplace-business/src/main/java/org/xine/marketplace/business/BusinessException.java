package org.xine.marketplace.business;

/**
 * The Class BusinessException.
 */
public class BusinessException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new business exception.
     * @param msg
     *            the msg
     */
    public BusinessException(final String msg) {
        super(msg);
    }

    /**
     * Instantiates a new business exception.
     * @param msg
     *            the msg
     * @param cause
     *            the cause
     */
    public BusinessException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
