package com.udacity.bootstrapemployeeapi.service;

import com.udacity.bootstrapemployeeapi.repository.EmployeeRepository;
import com.udacity.bootstrapemployeeapi.model.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee existingEmployee = optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
        existingEmployee.setName(employee.getName());
        existingEmployee.setSurname(employee.getSurname());
        existingEmployee.setGrade(employee.getGrade());
        existingEmployee.setSalary(employee.getSalary());
        employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> findEmployee(Specification<Employee> employeeSpecification) {
        return employeeRepository.findAll(employeeSpecification);
    }
}
