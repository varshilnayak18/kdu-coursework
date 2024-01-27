package com.example.assignment2.exceptions;

import com.example.assignment2.dto.ErrorDTO;
import com.example.assignment2.exceptions.custom.InvalidRequestParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling custom and runtime exceptions in the application
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler for custom InvalidRequestParameterException
     * @param exception InvalidRequestParameterException to be handled
     * @return ResponseEntity containing the ErrorDTO and HTTP status code
     */
    @ExceptionHandler(InvalidRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidRequestParameterException exception){
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for RuntimeException
     * @param exception RuntimeException to be handled
     * @return ResponseEntity containing the ErrorDTO and HTTP status code
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleError(RuntimeException exception){
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
}
