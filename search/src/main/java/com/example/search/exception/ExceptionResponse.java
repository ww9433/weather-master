package com.example.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResponse {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerException(Exception ex,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>("Null Value", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Need to check the server", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {

        return new ResponseEntity<>("Cannot find the requested resources", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
