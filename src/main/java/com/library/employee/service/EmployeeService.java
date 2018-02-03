package com.library.employee.service;

import com.library.employee.model.Employee;
import com.library.employee.model.EmployeeResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


public interface EmployeeService {
    public EmployeeResponse save(Employee employee);
    public EmployeeResponse getOne(Integer employeeId);
    public List<EmployeeResponse> getAll();
    public EmployeeResponse demote(Integer employeeId);
    public EmployeeResponse promote(Integer employeeId);

    List<EmployeeResponse> search(String firstName, String lastName, String employeeStatus, Integer employeeType);

    EmployeeResponse update(Integer employeeId,Employee employee);
}
