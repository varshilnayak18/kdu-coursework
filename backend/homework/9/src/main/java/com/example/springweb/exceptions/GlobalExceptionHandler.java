package com.example.springweb.exceptions;

import com.example.springweb.dto.ErrorDTO;
import com.example.springweb.exceptions.custom.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * class for handling exceptions for whole application
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * returns response entity of errorDTO with error message and HTTP status code when there is invalid argument
     * @param exception InvalidArgumentException
     * @return ResponseEntity
     */
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidArgumentException exception){
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * returns response entity of errorDTO with error message and HTTP status code when there is invalid response body passed
     * @param exception HttpMessageNotReadableException
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> methodArgument(HttpMessageNotReadableException exception){
        ErrorDTO errorDTO = new ErrorDTO("Method arguments invalid",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }

    /**
     * returns response entity of errorDTO with error message and HTTP status code when the list is empty
     * @param exception NullPointerException
     * @return ResponseEntity
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointer(NullPointerException exception){
        ErrorDTO errorDTO = new ErrorDTO("No vehicles in inventory currently", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }
}
