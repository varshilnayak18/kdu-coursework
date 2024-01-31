package com.spring.jpa.exception;

import com.spring.jpa.dto.ErrorDTO;
import com.spring.jpa.exception.custom.DataNotFoundException;
import com.spring.jpa.exception.custom.ShiftNotFoundException;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling specific custom exceptions in the Spring MVC application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for handling instances of DataNotFoundException.
     * @param ex The instance of DataNotFoundException.
     * @return ResponseEntity with an ErrorDTO containing error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(DataNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for handling instances of UnprocessableEntityException.
     * @param ex The instance of UnprocessableEntityException.
     * @return ResponseEntity with an ErrorDTO containing error details and HTTP status UNPROCESSABLE_ENTITY.
     */
    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<ErrorDTO> handleUnProcessableEntityException(UnprocessableEntityException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ShiftNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleShiftNotFoundException(ShiftNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for handling instances of Exception.
     * @param ex The instance of Exception.
     * @return ResponseEntity with an ErrorDTO containing error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
