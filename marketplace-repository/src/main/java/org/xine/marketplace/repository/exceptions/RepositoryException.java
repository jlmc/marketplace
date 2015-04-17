package org.xine.marketplace.repository.exceptions;


/**
 * The Class RepositoryException.
 */
public class RepositoryException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instantiates a new repository exception.
	 *
	 * @param msg the msg
	 */
	public RepositoryException(String msg){
		super(msg);
	}
	
	/**
	 * Respository exception.
	 *
	 * @param msg the msg
	 * @param throwable the throwable
	 */
	public RepositoryException(String msg, Throwable throwable){
		super(msg, throwable);
	}
	

}
