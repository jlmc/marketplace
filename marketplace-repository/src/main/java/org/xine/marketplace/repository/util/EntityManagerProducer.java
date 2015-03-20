package org.xine.marketplace.repository.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class EntityManagerProducer.
 */
@ApplicationScoped
public class EntityManagerProducer implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	 /** The factory. */
 	private final EntityManagerFactory factory;

	/**
	 * Instantiates a new entity manager producer.
	 */
	public EntityManagerProducer() {
		super();
		this.factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
	}
	
	
	 /**
	* Creates the.
	* @return the entity manager
	*/
	@Produces
	@RequestScoped
	public EntityManager create() {
	return this.factory.createEntityManager();
	}
	/**
	* Close.
	* @param manager
	* the manager
	*/
	@SuppressWarnings("static-method")
	public void close(@Disposes final EntityManager manager) {
	manager.close();
	}

}
