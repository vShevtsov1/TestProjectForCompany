package com.example.TestProjectForCompany.repo;

import com.example.TestProjectForCompany.entities.employees;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface employeesRepo  {
    List<employees> getAll();
    ResponseEntity<employees> getById(Long id);
    ResponseEntity<employees> save(employees employees);
    ResponseEntity<employees> delete(Long id);
    

}
