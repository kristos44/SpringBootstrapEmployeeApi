package com.udacity.bootstrapemployeeapi.service;

import com.udacity.bootstrapemployeeapi.model.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void addEmployee(Employee employee);

    void updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<Employee> findEmployee(Specification<Employee> employeeSpecification);
}
