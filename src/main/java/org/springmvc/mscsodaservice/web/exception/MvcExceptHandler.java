package org.springmvc.mscsodaservice.web.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class MvcExceptHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandlet(ConstraintViolationException e)
    {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation ->
        {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException ex) {
        List<String> errors = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError ->
        {
            errors.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
        });
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgsException(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errorMap.put(error.getField() , error.getDefaultMessage()));

        return errorMap;
    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        List<String> errors = new ArrayList<>();

        ex.getPath().forEach(path -> {
            String fieldName = path.getFieldName();
            String errorMessage = "Invalid format for field: " + fieldName + ". Invalid value: " + ex.getValue();
            errors.add(errorMessage);

        });

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

}
