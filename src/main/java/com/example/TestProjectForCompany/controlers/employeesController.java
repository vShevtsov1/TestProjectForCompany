package com.example.TestProjectForCompany.controlers;

import com.example.TestProjectForCompany.entities.employees;
import com.example.TestProjectForCompany.repo.employeesRepo;
import com.example.TestProjectForCompany.services.employeesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class employeesController implements employeesRepo {

    private final employeesService employeesService;

    public employeesController(com.example.TestProjectForCompany.services.employeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    @GetMapping()
    @Operation(summary = "Get all data from tblEmployees")
    public List<employees> getAll() {
        return employeesService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    public ResponseEntity<employees> getById(@Parameter(description = "employee id to find") @PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(employeesService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PostMapping("/save")
    @Operation(summary = "Endpoint to save or update new/exists employee")
    public ResponseEntity<employees> save(@Parameter(description = "json body with info about employee", required = true) @RequestBody employees employees1) {
        try {
            return ResponseEntity.ok(employeesService.save(employees1));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Endpoint to delete exists employee by id")
    @ApiResponse(responseCode = "200", description = "employee deleted")
    @ApiResponse(responseCode = "404", description = "employee not found")
    public ResponseEntity<employees> delete(@Parameter(description = "employee id to delete") @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(employeesService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @GetMapping("/search/{partOfName}")
    @Operation(summary = "Endpoint to search all employees by part of the name")
    public List<employees> search(@Parameter(description = "part of name to be find") @PathVariable("partOfName") String partOfName) {
        return employeesService.search(partOfName);
    }

}
