package org.xine.marketplace.repository.events;

import javax.enterprise.event.Event;
//import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;


//@NoUniqueResultNotify
public class NoUniqueResultHandler {
	
	//@Inject
    private Event<NonUniqueResultException> itemEvent;
	
	
	public void handleItem(NonUniqueResultException item) {
        System.out.println("Firing Event");
        itemEvent.fire(item);
    }

}
