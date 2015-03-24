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
 * <p>
 * Interceptor for methods marked with the annotation {@code Transactional}. The invoke method is
 * called before the scheduled method and at the end of this work. Before entering in the annotated
 * method a transaction is open, at the end of the annotated method work if there were no problem
 * {@code EntityTransaction#commit()} is executed, otherwise {@code EntityTransaction#rollback()} is
 * executed.
 * </p>
 * <p>
 * Notes: Using this interceptor the transaction in handler at the application level. in other
 * words, the transaction type defined in the file persitence.xml is RESOURCE_LOCAL.
 * {@code
 *  transaction-type="RESOURCE_LOCAL".
 * } <br>
 * if we use a JEE conteiner (JBOSS for exemple) and we want that the container has the
 * responsibility for opening and closing the Transactions. this interceptor is no longer
 * recommended, and the persistence.xml file defines the transation type has:
 * {@code
 *  transaction-type="JTA".
 * }
 * <p>
 * <i> NOTES: This is a CDI Interceptor, and so, to be able to use him, it must be marked in the
 * bean.xml file: {@code
 *
 * <interceptors>
 *   <class>org.xine.marketplace.repository.util.TransactionInterceptor</class>
 * </interceptors>
 *
 * } </i>
 * </p>
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
     *            the context
     * @return the object
     * @throws Exception
     *             the exception
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
