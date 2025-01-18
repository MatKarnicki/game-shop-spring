package ug.edu.game.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAfterValidator implements ConstraintValidator<DateAfter, LocalDate> {

    private LocalDate specifiedDate;

    @Override
    public void initialize(DateAfter constraintAnnotation) {
        this.specifiedDate = LocalDate.parse(constraintAnnotation.value(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null checks
        }
        return value.isAfter(specifiedDate);
    }
}
