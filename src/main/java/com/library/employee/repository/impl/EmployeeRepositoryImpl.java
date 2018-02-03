package com.library.employee.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.employee.EmployeeApplication;
import com.library.employee.model.Employee;
import com.library.employee.repository.EmployeeRepository;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


@Data
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    File file = new File("employees.json");
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public EmployeeRepositoryImpl() {
        if (!this.file.exists())
        {
            this.file.createNewFile();
            objectMapper.writeValue(new FileOutputStream(file), new ArrayList<>());
        }
    }

    @Override
    @SneakyThrows
    public void exportStorage(List<Employee> employees) {
        objectMapper.writeValue(new FileOutputStream(file), employees);
    }

    @SneakyThrows
    public  List<Employee> importStorage() {
        List<Employee> employees= new ArrayList<Employee>();
        employees=objectMapper.readValue(new FileInputStream(file), new TypeReference<ArrayList<Employee>>() {});
        return employees;
    }


}
