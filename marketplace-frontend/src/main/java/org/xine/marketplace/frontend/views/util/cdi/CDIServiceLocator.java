package org.xine.marketplace.frontend.views.util.cdi;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The Class CDIServiceLocator.
 */
public class CDIServiceLocator {

    /**
     * Gets the bean manager.
     * @return the bean manager
     */
    private static BeanManager getBeanManager() {
        try {
            final InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/env/BeanManager");
        } catch (final NamingException e) {
            throw new RuntimeException("Could not find BeanManager no JNDI.");
        }
    }

    /**
     * Gets the bean.
     * @param <T>
     *            the generic type
     * @param clazz
     *            the clazz
     * @return the bean
     */
    @SuppressWarnings({"unchecked" })
    public static <T> T getBean(final Class<T> clazz) {
        final BeanManager bm = getBeanManager();
        final Set<Bean<?>> beans = bm.getBeans(clazz);

        if (beans == null || beans.isEmpty()) {
            return null;
        }

        final Bean<T> bean = (Bean<T>) beans.iterator().next();

        final CreationalContext<T> ctx = bm.createCreationalContext(bean);
        final T o = (T) bm.getReference(bean, clazz, ctx);

        return o;
    }

}
