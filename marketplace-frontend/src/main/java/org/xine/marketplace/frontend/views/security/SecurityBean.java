package org.xine.marketplace.frontend.views.security;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * The Class SecurityBean.
 */
@Named
@RequestScoped
public class SecurityBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The external context. */
    @Inject
    private ExternalContext externalContext;

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        String username = null;

        final SystemUser systemUser = getSessionUser();
        if (systemUser != null) {
            username = systemUser.getUser().getUsername();
        }
        return username;
    }

    /**
     * Gets the session user.
     * @return the session user
     */
    @SuppressWarnings("static-method")
    private SystemUser getSessionUser() {
        final Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        final UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;

        if (auth != null && auth.getPrincipal() != null) {
            return (SystemUser) auth.getPrincipal();
        }

        return null;
    }

    /**
     * Can issue requisition.
     * @return true, if successful
     */
    public boolean isPermissionToIssueRequisition() {
        return this.externalContext.isUserInRole("ADMIN") || this.externalContext.isUserInRole("SELLER");
    }

    /**
     * Can cancel requisition.
     * @return true, if successful
     */
    public boolean isPermissionToCancelRequisition() {
        return this.externalContext.isUserInRole("ADMIN") || this.externalContext.isUserInRole("SELLER");
    }

}
