package com.eventticketingsystem.exceptions;

import com.eventticketingsystem.records.CommonResponseBody;
import com.eventticketingsystem.records.Error;
import com.eventticketingsystem.records.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<CommonResponseBody> handleConstraintViolationException(ConstraintViolationException e) {
        List<Error> errorList = new ArrayList<>();
        for (ConstraintViolation violationException : e.getConstraintViolations()) {
            Optional<Path.Node> leaf = getLeafNode(violationException.getPropertyPath());
            String errorMessage = violationException.getPropertyPath().toString();

            errorList.add(new Error(errorMessage, HttpStatus.BAD_REQUEST.value(), violationException.getMessage()));
        }
        errorList.sort(Comparator.comparing(Error::name));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, new ErrorResponse(errorList));
        return new ResponseEntity<>(commonResponseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<CommonResponseBody> handleHttpMessageNotReadableException(MethodArgumentNotValidException e) {
        List<Error> errorList = new ArrayList<>();

        for (ObjectError violationException: e.getBindingResult().getAllErrors()) {
            if (violationException instanceof FieldError fieldViolationException) {
                errorList.add(new Error(fieldViolationException.getField(), HttpStatus.BAD_REQUEST.value(), fieldViolationException.getDefaultMessage()));
            }
            else {
                errorList.add(new Error(violationException.getObjectName(), HttpStatus.BAD_REQUEST.value(), violationException.getDefaultMessage()));
            }
        }

        errorList.sort(Comparator.comparing(Error::name));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, new ErrorResponse(errorList));
        return new ResponseEntity<>(commonResponseBody, HttpStatus.BAD_REQUEST);
    }

    // Handle Data Consistency Error | 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<CommonResponseBody> handleDataConsistencyError(DataIntegrityViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(new Error(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), e.getMessage())));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, errorResponse);
        return new ResponseEntity<>(commonResponseBody, HttpStatus.CONFLICT);
    }

    // Handle Missing Resource Error | 404
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<CommonResponseBody> handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(new Error(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), e.getMessage())));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, errorResponse);
        return new ResponseEntity<>(commonResponseBody, HttpStatus.NOT_FOUND);
    }

    // Handle Missing Required Resource Error | 400
    @ExceptionHandler(ServletRequestBindingException.class)
    ResponseEntity<CommonResponseBody> handleMissingParameterException(ServletRequestBindingException requestBindingException) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(new Error(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), requestBindingException.getMessage())));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, errorResponse);
        return new ResponseEntity<>(commonResponseBody, HttpStatus.BAD_REQUEST);
    }

    // Handle Internal Server Error | 500
    @ExceptionHandler({RuntimeException.class, ServiceException.class})
    ResponseEntity<CommonResponseBody> handleRuntimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(new Error(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(), requestBindingException.getMessage())));
        CommonResponseBody commonResponseBody = new CommonResponseBody(null, null, errorResponse);
        return new ResponseEntity<>(commonResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Optional<Path.Node> getLeafNode(Path path) {
        return StreamSupport.stream(path.spliterator(), false).reduce((a, b) -> b);
    }
}
