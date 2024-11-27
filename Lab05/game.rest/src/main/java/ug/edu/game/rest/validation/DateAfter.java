package ug.edu.game.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Link the annotation to its validator
@Constraint(validatedBy = DateAfterValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAfter {
    String message() default "Date must be after {value}";

    String value(); // Fixed date in "yyyy-MM-dd" format

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
