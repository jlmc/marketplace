package org.xine.marketplace.frontend.views.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SecurityBean {
	
	
	public String getUsername(){
		return "Master-user";
	}

}
