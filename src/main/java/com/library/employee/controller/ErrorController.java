package com.library.employee.controller;

import com.library.employee.exseption.AbstractAPIException;
import com.library.employee.exseption.InputValidationException;
import com.library.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value =AbstractAPIException.class)
    public ResponseEntity<ErrorResponse> getErrorResponse(AbstractAPIException e) {
        return new ResponseEntity<>(e.getErrorResponse(),e.getHttpStatusCode());
    }


}