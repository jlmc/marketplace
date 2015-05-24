package org.xine.marketplace.validator.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * Validate that the annotated string represent a valid SKU Number.
 * the sku two chars from 'a' a 'Z' and 4 a 18 digits, that is optinal (?- validate just when is
 * something is present)
 * @author Joao Costa
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
    // @Pattern(regexp="([a-zA-Z]{2}\\d{4,18})?")
    /**
     * Message.
     * @return the string
     */
    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "{org.xine.marketplace.validator.constraints.SKU.message}";

    /**
     * Groups.
     * @return the class[]
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};
}
