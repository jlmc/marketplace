package org.xine.marketplace.repository.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * The Interface Transactional.
 * existe um inteceptador desta anotação, das chamadas dos metodos anotados com esta anotação.
 * {@code TransactionInterceptor#invoke(javax.interceptor.InvocationContext)}
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {
    // nothing
}
