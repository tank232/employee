package com.library.employee.exseption;

import com.library.employee.model.Employee;
import com.library.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputValidationException extends AbstractAPIException {

    private BindingResult result;

    public InputValidationException(BindingResult result) {
        super(HttpStatus.BAD_REQUEST);
        this.result = result;
    }



    public ErrorResponse getErrorResponse() {
        if (this.errorResponse == null) {
            this.errorResponse = new ErrorResponse();
        }

        List<FieldError> fieldErrors =
                this.result.getFieldErrors();

        Map<String, List<String>> errors =
                fieldErrors
                        .stream()
                        .collect(
                                Collectors
                                        .groupingBy(
                                                x->x.getField()
                                                , Collectors.mapping(x->x.getDefaultMessage()
                                                        , Collectors.toList()
                                                )
                                        )
                        );

/*
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            if (errors.get(fieldError.getField()) == null) {

                errors.put(fieldError.getField(), new ArrayList<>());
            }
            errors.get(fieldError.getField())
                    .add(fieldError.getDefaultMessage());
        }*/


        this.errorResponse.setErrors(errors);

        return this.errorResponse;
    }


}
