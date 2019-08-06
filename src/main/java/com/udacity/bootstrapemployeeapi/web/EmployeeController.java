package com.udacity.bootstrapemployeeapi.web;

import com.udacity.bootstrapemployeeapi.model.Employee;
import com.udacity.bootstrapemployeeapi.service.EmployeeService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<List<Employee>>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return new ResponseEntity<Employee>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee/find")
    public List<Employee> findEmployee(@RequestParam(required=false) String name,
                                       @RequestParam(required=false) String surname,
                                       @RequestParam(required=false) String grade,
                                       @RequestParam(required=false) String salary) {
        Specification<Employee> employeeSpecification = Specification.where(new EmployeeWithName(name))
                .and(new EmployeeWithSurname(surname))
                .and(new EmployeeWithGrade(grade))
                .and(new EmployeeWithSalary(salary));
        return employeeService.findEmployee(employeeSpecification);
    }

    class EmployeeWithName implements Specification<Employee> {

        private String name;

        public EmployeeWithName(String name) {
            this.name = name;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (name == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("name"), this.name);
        }
    }

    class EmployeeWithSurname implements Specification<Employee> {

        private String surname;

        public EmployeeWithSurname(String surname) {
            this.surname = surname;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (surname == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("surname"), this.surname);
        }
    }

    class EmployeeWithGrade implements Specification<Employee> {

        private String grade;

        public EmployeeWithGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (grade == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("grade"), this.grade);
        }
    }

    class EmployeeWithSalary implements Specification<Employee> {

        private String salary;

        public EmployeeWithSalary(String salary) {
            this.salary = salary;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (salary == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("salary"), this.salary);
        }
    }
}
