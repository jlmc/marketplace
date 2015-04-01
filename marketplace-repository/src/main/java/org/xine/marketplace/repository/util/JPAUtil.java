package org.xine.marketplace.repository.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class JPAUtil.
 */
public final class JPAUtil implements Constants {
    /**
     * Instantiates a new JPA util.
     */
    private JPAUtil() {
    }

    /** The emf. */
    private static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /**
     * Creates the entity manager.
     * @return the entity manager
     */
    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}
