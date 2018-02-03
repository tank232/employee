package com.library.employee.service.impl;

import com.library.employee.exseption.AbstractAPIException;
import com.library.employee.exseption.EmployeeStatusMismatchException;
import com.library.employee.exseption.GeneralAPIException;
import com.library.employee.exseption.InputValidationException;
import com.library.employee.model.Employee;
import com.library.employee.model.EmployeeResponse;
import com.library.employee.model.EmployeeStatus;
import com.library.employee.repository.EmployeeRepository;
import com.library.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    int flagLoadRepository =0;
    List<Employee> employees=new ArrayList<Employee>();
    private static AtomicInteger incriment = new AtomicInteger();

    public void setEmployees() {
      if (flagLoadRepository==0) {
              employees = employeeRepository.importStorage();
              if (employees!=null)
              {incriment.set(employees.stream().mapToInt(x -> x.getId()).max().orElse(0));}
          flagLoadRepository=1;
      }
    }

    @Override
    public EmployeeResponse save(Employee employee) {
        setEmployees();
        employee.setEmployeeStatus(EmployeeStatus.CANDIDATE);
        employees.add(employee);
        incriment.getAndIncrement();
        employee.setId(incriment.get());
        employeeRepository.exportStorage(employees);
        return getEmployeeResponse(employee);
    }

    private EmployeeResponse getEmployeeResponse(Employee employee) {
        setEmployees();
        if (employee!=null )
        {
            return new EmployeeResponse(employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getEmployeeType().getId(),employee.getEmployeeStatus());}
        return null;
    }

    public EmployeeResponse getOne(Integer employeeId)
    {
        return getEmployeeResponse(getEmployee(employeeId));
    }

    public List<EmployeeResponse> getAll()
    {
        setEmployees();
        return employees.stream().map(x->getEmployeeResponse(x)).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse demote(Integer employeeId) {
        Employee employee=getEmployee(employeeId);
        if(employee !=null)
        {
            if(employee.getEmployeeStatus()==EmployeeStatus.HIRED)
            {
                if( employee.getEmployeeType().getDemoteType()!=null) {
                    employee.setEmployeeType(employee.getEmployeeType().getDemoteType());
                    employeeRepository.exportStorage(employees);
                }
                 else
                {throw new GeneralAPIException("Can not be demoted Type");}
                return getEmployeeResponse(employee);
            }
            else
            { throw new EmployeeStatusMismatchException();}
        }
        else
        {throw new GeneralAPIException("Employee not found");}
    }

    @Override
    public EmployeeResponse promote(Integer employeeId) {
        Employee employee=getEmployee(employeeId);
        if(employee !=null) {
            if (employee.getEmployeeStatus() == EmployeeStatus.HIRED) {
                if (employee.getEmployeeType().getPromoteType() != null) {
                    employee.setEmployeeType(employee.getEmployeeType().getPromoteType());
                    employeeRepository.exportStorage(employees);
                } else {
                    throw new GeneralAPIException("Can not be promoted Type");
                }
                return getEmployeeResponse(employee);
            } else {
                throw new EmployeeStatusMismatchException();
            }
        }
         else
            {throw new GeneralAPIException("Employee not found");}
    }

    @Override
    public List<EmployeeResponse> search(String firstName, String lastName, String employeeStatus, Integer employeeType) {
        setEmployees();
        return

                employees.stream().filter(x->
                        ( firstName==null || x.getFirstName().equals(firstName)) &&
                                (lastName==null  || x.getLastName().equals(lastName)) &&
                                (employeeStatus==null  || x.getEmployeeStatus().name().equals(employeeStatus)) &&
                                (employeeType==null  || x.getEmployeeType().getId().equals(employeeType))
                ).map(x->getEmployeeResponse(x)).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse update(Integer employeeId,Employee employee) {
        setEmployees();

        for (int i = 0; i <employees.size() ; i++) {
            if (employees.get(i).getId() == employeeId) {
                if( employees.get(i).getEmployeeType()==employee.getEmployeeType())
                {
                    employee.setId(employeeId);
                    employees.set(i,employee);
                    employeeRepository.exportStorage(employees);
                    return getEmployeeResponse(employee);
                }
                else
                { throw new GeneralAPIException("Can not be changed Type");}
            }
        }
        throw new GeneralAPIException("Employee not found");
    }

    public Employee getEmployee(Integer employeeId)
    {
        setEmployees();
        return employees.stream().filter(x->x.getId().equals(employeeId)).findFirst().orElse(null);
    }


}
