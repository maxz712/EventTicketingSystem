package com.eventticketingsystem.annotations;

import com.eventticketingsystem.entity.EventRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidEventDates.Validator.class})
public @interface ValidEventDates {
    String message() default "{date.inPast}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ValidEventDates, EventRequest> {

        public void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
            // Remove Default
            constraintContext.disableDefaultConstraintViolation();

            // Set new message
            constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        @Override
        public boolean isValid(EventRequest eventRequest, ConstraintValidatorContext constraintValidatorContext) {
            // Don't complain if eventDate is null since those errors will be caught elsewhere
            if (eventRequest.getEventDate() == null)
                return true;

            Instant eventDate = eventRequest.getEventDate().toInstant();

            // Validate Event is not created in the past
            // Add 1 hour leniency window since:
            //  1. There may be delay between event creating request and us receiving it
            //  2.
        }
    }
}
