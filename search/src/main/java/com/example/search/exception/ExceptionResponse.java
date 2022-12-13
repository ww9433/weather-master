package com.example.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionResponse {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerException(Exception ex,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>("Null Value " + HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof ResourceNotFoundException) {
            return new ResponseEntity<>("Cannot find the requested resources " + HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Need to check the server " + HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
