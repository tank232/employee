package com.library.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Employee {
    Integer id;
    @NotBlank
    @Length(min = 2,max=30,message="Length between 2 and 30")
    String firstName;
    @NotBlank
    @Length(min = 2,max=30,message="Length between 2 and 30")
    String lastName;
    EmployeeType employeeType;
    EmployeeStatus employeeStatus;
}

