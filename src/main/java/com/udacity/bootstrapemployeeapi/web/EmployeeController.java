package com.udacity.bootstrapemployeeapi.web;

import com.udacity.bootstrapemployeeapi.model.Employee;
import com.udacity.bootstrapemployeeapi.service.EmployeeService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
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
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<Employee>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee/find")
    public List<Employee> findEmployee(@RequestParam(required=false) String name,
                                       @RequestParam(required=false) String surname,
                                       @RequestParam(required=false) String address,
                                       @RequestParam(required=false) String job) {
        Specification<Employee> employeeSpecification = Specification.where(new EmployeeWithName(name))
                .and(new EmployeeWithSurname(surname))
                .and(new EmployeeWithAddress(address))
                .and(new EmployeeWithJob(job));
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

    class EmployeeWithAddress implements Specification<Employee> {

        private String address;

        public EmployeeWithAddress(String address) {
            this.address = address;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (address == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("address"), this.address);
        }
    }

    class EmployeeWithJob implements Specification<Employee> {

        private String job;

        public EmployeeWithJob(String job) {
            this.job = job;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            if (job == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
            }
            return criteriaBuilder.equal(root.get("job"), this.job);
        }
    }
}
