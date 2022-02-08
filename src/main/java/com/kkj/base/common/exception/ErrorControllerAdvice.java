package com.kkj.base.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        log.debug("ErrorControllerAdvice = " + exceptionAsString);

        ErrorResponse response = ErrorResponse.of(ErrorMessage.TEMPORARY_SERVER_ERROR);
        response.setDetail(e.getMessage());
        response.setUuid(UUID.randomUUID().toString());
        response.setZonedDateTime(ZonedDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception e) {
        ErrorResponse response = ErrorResponse.of(ErrorMessage.RESOURCE_NOT_FOUND);
        response.setDetail(e.getMessage());
        response.setUuid(UUID.randomUUID().toString());
        response.setZonedDateTime(ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /* ApiException Error Handler */
    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleCustomException(ApiException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        response.setDetail(e.getMessage());
        response.setUuid(UUID.randomUUID().toString());
        response.setZonedDateTime(ZonedDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
}
