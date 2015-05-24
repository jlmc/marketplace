package org.xine.marketplace.validator.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Validate that the annotated string is not {@code null} or empty.
 * The difference to {@code NotEmpty} is that trailing whitespaces are getting ignored.
 * @author joao costa
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "((?=\\s*\\S).*$)")
@NotNull
@ReportAsSingleViolation
public @interface NotBlank {
    // "^(?=\\s*\\S).*$"

    /**
     * Message.
     * @return the string
     */
    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "{org.xine.marketplace.validator.constraints.NotBlank.message}";

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
