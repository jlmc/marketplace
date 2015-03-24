package org.xine.marketplace.repository.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * The Interface Transactional.
 * <p>
 * Annotation to mark the methods that implements a critical works or just some persistence work.
 * There is an interceptor of this note, the calls of methods annotated with this annotation.
 * </p>
 * @see TransactionInterceptor
 * @author jcosta
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {
    // nothing
}
