package org.xine.marketplace.frontend.views.controller.requisitions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * The RequisitionEditer Annotation is use to Qualifier the actual Requisitation in Use.
 * @author Joao Costa
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
public @interface RequisitionEditer {
    // nothing
}
