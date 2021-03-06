package org.xine.marketplace.frontend.views.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.xine.marketplace.model.entities.User;

/**
 * The Class SystemUser. It is the representation of the Session user, using the spring security
 * framework.
 * @author Joao Costa
 */
public class SystemUser extends org.springframework.security.core.userdetails.User {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user. */
    private final User user;

    /**
     * Instantiates a new system user.
     * @param user
     *            the user
     * @param authorities
     *            the authorities
     */
    public SystemUser(final User user, final Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    /**
     * Gets the user.
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

}
