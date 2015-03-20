package org.xine.marketplace.repository.util;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * The Class TransactionInterceptor.
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The manager. */
	@Inject
	private EntityManager manager;
	
	/**
	 * Invoke.
	 * @param context
	 * the context
	 * @return the object
	 * @throws Exception
	 * the exception
	 */
	@AroundInvoke
	public Object invoke(final InvocationContext context) throws Exception {
		final EntityTransaction transaction = this.manager.getTransaction();
		boolean owner = false;
		try {
			if (!transaction.isActive()) {
				// Trick to rollback what has passed
				// (If not , a future commit, confirm even without transaction operations )
				transaction.begin();
				transaction.rollback();
				// now w can start the transaction
				transaction.begin();
				owner = true;
			}
			// call the method annoteted
			return context.proceed();
		} catch (final Exception e) {
			if (transaction != null && owner) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && owner) {
				transaction.commit();
			}
		}
	}
}