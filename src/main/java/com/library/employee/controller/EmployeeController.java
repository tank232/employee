package com.library.employee.controller;

import com.library.employee.exseption.InputValidationException;
import com.library.employee.model.Employee;
import com.library.employee.model.EmployeeResponse;
import com.library.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

// create a new employee

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public EmployeeResponse save(
            @RequestBody @Valid Employee employee,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return employeeService.save(employee);
    }

    //get employee by id
    @GetMapping("/get/{employeeId}")
    public EmployeeResponse getOne(
            @PathVariable("employeeId") Integer employeeId) {
        return employeeService.getOne(employeeId);
    }

    //get employees list
    @GetMapping("/get")
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    // update employee info
    @PutMapping("/update")
    public EmployeeResponse update(
            @RequestHeader Integer employeeId,
            @RequestBody @Valid Employee employee,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return employeeService.update(employeeId,employee);
    }

    //promote employee
    @PutMapping("/promote")
    public EmployeeResponse promote(
            @RequestHeader Integer employeeId) {
        return employeeService.promote(employeeId);
    }

    //demote employee
    @PutMapping("/demote")
    public EmployeeResponse demote(
            @RequestHeader Integer employeeId) {
        return employeeService.demote(employeeId);
    }

    //search employees
    @GetMapping("/search")
    public List<EmployeeResponse> search(
            @RequestParam Map<String,String> allRequestParams
            ) {
        String firstName=allRequestParams.get("firstName");
        String lastName=allRequestParams.get("lastName");
        String employeeStatus=allRequestParams.get("employeeStatus");
        if(allRequestParams.get("employeeType") !=null) {
            Integer employeeType = Integer.valueOf(allRequestParams.get("employeeType"));
            return  employeeService.search(firstName,lastName,employeeStatus,employeeType);
        }

        return  employeeService.search(firstName,lastName,employeeStatus,null);
    }
}
