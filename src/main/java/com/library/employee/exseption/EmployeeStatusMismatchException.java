package com.library.employee.exseption;

import com.library.employee.model.Employee;
import com.library.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.*;

public class EmployeeStatusMismatchException extends AbstractAPIException {

    public EmployeeStatusMismatchException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        if (this.errorResponse == null) {
            this.errorResponse = new ErrorResponse();
        }
        Map<String, List<String>> errors =new HashMap<>();
        errors.put("Employee status not valid",new ArrayList<>());
        this.errorResponse.setErrors(errors);
        return this.errorResponse;
    }
}
