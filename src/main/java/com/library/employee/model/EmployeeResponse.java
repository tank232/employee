package com.library.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeResponse {
    Integer id;
    String firstName;
    String lastName;
    Integer employeeType;
    EmployeeStatus employeeStatus;
}
