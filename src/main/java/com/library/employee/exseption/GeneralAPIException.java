package com.library.employee.exseption;

import com.library.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralAPIException extends AbstractAPIException {
    String exception;

    public GeneralAPIException(String exception) {
        super(HttpStatus.METHOD_NOT_ALLOWED);
            this.exception=exception;
        }



    @Override
    public ErrorResponse getErrorResponse() {
        if (this.errorResponse == null) {
            this.errorResponse = new ErrorResponse();
        }
        Map<String, List<String>> errors =new HashMap<>();
        errors.put(exception,new ArrayList<>());
        this.errorResponse.setErrors(errors);
        return this.errorResponse;
    }
}