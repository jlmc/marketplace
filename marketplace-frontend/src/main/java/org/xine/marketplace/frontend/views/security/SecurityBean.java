package org.xine.marketplace.frontend.views.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * The Class SecurityBean.
 */
@Named
@RequestScoped
public class SecurityBean {

    // TODO:: missing SecurityBean provider

    /**
     * Gets the username.
     * @return the username
     */
    @SuppressWarnings("static-method")
    public String getUsername() {
        return "Master-user";
    }

}
