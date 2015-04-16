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
	 *
	 * @return the bean manager
	 */
	private static BeanManager getBeanManager() {
		try {
			InitialContext initialContext = new InitialContext();
			return (BeanManager) initialContext.lookup("java:comp/env/BeanManager");
		} catch (NamingException e) {
			throw new RuntimeException("Could not find BeanManager no JNDI.");
		}
	}

	/**
	 * Gets the bean.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @return the bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		BeanManager bm = getBeanManager();
		Set<Bean<?>> beans = (Set<Bean<?>>) bm.getBeans(clazz);

		if (beans == null || beans.isEmpty()) {
			return null;
		}

		Bean<T> bean = (Bean<T>) beans.iterator().next();

		CreationalContext<T> ctx = bm.createCreationalContext(bean);
		T o = (T) bm.getReference(bean, clazz, ctx);

		return o;
	}


}
