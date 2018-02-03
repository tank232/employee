package com.library.employee.exseption;

import com.library.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public abstract class AbstractAPIException extends RuntimeException {
    protected ErrorResponse errorResponse;

    public AbstractAPIException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    HttpStatus httpStatus;
    public HttpStatus getHttpStatusCode() {
        return httpStatus;
    }

    public abstract ErrorResponse getErrorResponse();
}
