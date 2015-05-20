package org.xine.marketplace.frontend.views.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xine.marketplace.business.services.users.UserService;
import org.xine.marketplace.frontend.views.util.cdi.CDIServiceLocator;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetailsServices implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final UserService userService = CDIServiceLocator.getBean(UserService.class);

        final User user = userService.getUserByEmail(email);

        if (user != null) {
            final SystemUser systemUser = new SystemUser(user, getGroups(user));
            return systemUser;
        }

        return null;
    }

    @SuppressWarnings("static-method")
    private Collection<? extends GrantedAuthority> getGroups(final User user) {
        final List<SimpleGrantedAuthority> groups = new ArrayList<>();

        for (final Permission p : user.getPermissions()) {
            groups.add(new SimpleGrantedAuthority(p.getName().trim().toUpperCase()));
        }

        return groups;
    }

}
