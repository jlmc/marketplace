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
 * Validate that the annotated string represent a valid ZipCode Number.
 * the zip code is represents with two numbers separated with '-' char,
 * the first number may be 3 or 4 digits, and the second number may have 2 or 3 digits
 * (?- validate just when is something is present)
 * @author Joao Costa
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "(\\d{3,4}-\\d{2,3})?")
public @interface ZipCode {

    /**
     * Message.
     * @return the string
     */
    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "{org.xine.marketplace.validator.constraints.ZipCode.message}";

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
