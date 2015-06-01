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
public final class CDIServiceLocator {

    /**
     * Instantiates a new CDI service locator.
     */
    private CDIServiceLocator() {}

    /**
     * Gets the bean manager.
     * @return the bean manager
     */
    private static BeanManager getBeanManager() {
        final String[] names = new String[] {"java:comp/env/BeanManager", "java:comp/BeanManager" };
        for (final String name : names) {
            try {
                return lookup(name);
            } catch (final NamingException e) {
                //
            }
        }
        throw new RuntimeException("Could not find BeanManager no JNDI.");
    }

    /**
     * Lookup.
     * @param name
     *            the name
     * @return the t
     * @throws NamingException
     *             the naming exception
     */
    @SuppressWarnings("unchecked")
    private static <T> T lookup(final String name) throws NamingException {
        final InitialContext initialContext = new InitialContext();
        return (T) initialContext.lookup(name);
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
