package com.library.employee.repository;

import com.library.employee.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    public void exportStorage(List<Employee> employees);
    public List<Employee> importStorage();
}
