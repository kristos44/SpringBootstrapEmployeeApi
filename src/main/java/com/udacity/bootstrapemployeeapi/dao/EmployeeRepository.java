package com.udacity.bootstrapemployeeapi.dao;

import com.udacity.bootstrapemployeeapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

//    @Query(value = "select e.id, e.breed from Employee e")
//    List<String> findBreeds();
//
//    @Query("select e.id, e.breed from Employee e where e.id=:id")
//    String findBreedById(Long id);
}
