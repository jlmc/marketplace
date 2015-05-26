package org.xine.marketplace.frontend.views.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xine.marketplace.business.services.users.UserService;
import org.xine.marketplace.frontend.views.util.cdi.CDIServiceLocator;
import org.xine.marketplace.model.entities.User;

/**
 * The Class AppUserDetailsServices. spring security implementation to get the user by username or
 * email.
 * @author Joao Costa
 */
public class AppUserDetailsServices implements UserDetailsService {

    /**
     * Locates the user based on the email. In the actual implementation, the search may
     * possibly be case
     * sensitive, or case insensitive depending on how the implementation instance is configured.
     * In this case, the <code>UserDetails</code> object that comes back may have a username that
     * is of a different case than what was
     * actually requested..
     * @param email
     *            the email
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException
     *             if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final UserService userService = CDIServiceLocator.getBean(UserService.class);

        final User user = userService.getUserByEmail(email);

        if (user != null) {
            return new SystemUser(user, getGroups(user));
        }
        return null;
    }

    /**
     * Gets the groups permissions.
     * @param user
     *            the user
     * @return the groups
     */
    private static Collection<? extends GrantedAuthority> getGroups(final User user) {
        final List<SimpleGrantedAuthority> groups = new ArrayList<>();

        user.getPermissions().stream().forEach(p -> groups.add(new SimpleGrantedAuthority(p.getName().trim().toUpperCase())));

        return groups;
    }

}
