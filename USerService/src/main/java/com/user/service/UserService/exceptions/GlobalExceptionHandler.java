package com.user.service.UserService.exceptions;

import com.user.service.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(
                message,
                false, // use false since it's an error (NOT_FOUND)
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
}
