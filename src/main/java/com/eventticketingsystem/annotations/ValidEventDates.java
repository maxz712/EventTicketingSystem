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
    String message() default "{date.endBeforeStart}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ValidEventDates, EventRequest> {

        String startDateInPast = "{date.inPast}";

        public void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
            // Remove Default
            constraintContext.disableDefaultConstraintViolation();

            // Set new message
            constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        @Override
        public boolean isValid(EventRequest eventRequest, ConstraintValidatorContext constraintValidatorContext) {
            // Don't complain if eventDate is null since those errors will be caught elsewhere
            if (eventRequest.getStartTime() == null || eventRequest.getEndTime() == null)
                return true;

            Instant startTime = eventRequest.getStartTime().toInstant();
            Instant endTime = eventRequest.getEndTime().toInstant();

            // Validate Event is not created in the past
            if (startTime.isBefore(Instant.now())) {
                customMessageForValidation(constraintValidatorContext, startDateInPast);
                return false;
            }

            return startTime.isBefore(endTime);
        }
    }
}
