package org.xine.marketplace.frontend.views.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * The Qualifier SessionSystemUser.Is a CDI Qualifier used to Inject the current session user.
 * @author Joao Costa
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD })
public @interface SessionSystemUser {
    // nothing
}
