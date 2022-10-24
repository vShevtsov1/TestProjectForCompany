package com.example.TestProjectForCompany.repo;

import com.example.TestProjectForCompany.entities.departments;
import com.example.TestProjectForCompany.entities.employees;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface departmentsRepo {
    List<departments> getAll();

    ResponseEntity<departments> getById(Long id);

    ResponseEntity<departments> save(departments departments);

    ResponseEntity<departments> delete(Long id);
}
