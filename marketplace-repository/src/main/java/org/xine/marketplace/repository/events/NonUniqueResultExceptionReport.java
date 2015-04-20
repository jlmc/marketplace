package org.xine.marketplace.repository.events;

import javax.enterprise.event.Observes;
import javax.persistence.NonUniqueResultException;

/**
 * The Class NonUniqueResultExceptionReport.
 */
public class NonUniqueResultExceptionReport {

	/**
	 * Event fired.
	 *
	 * @param item the item
	 */
	public void eventFired(@Observes NonUniqueResultException item) {
        handleItem(item);
    }

	/**
	 * Handle item.
	 *
	 * @param item the item
	 */
	private void handleItem(NonUniqueResultException item) {
		// TODO Auto-generated method stub
		System.out.println("handleItem NonUniqueResultException: "+ item.getMessage());
	}
}
