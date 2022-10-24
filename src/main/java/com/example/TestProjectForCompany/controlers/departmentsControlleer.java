package com.example.TestProjectForCompany.controlers;

import com.example.TestProjectForCompany.entities.departments;
import com.example.TestProjectForCompany.repo.departmentsRepo;
import com.example.TestProjectForCompany.services.departmentsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping(path = "/departments")
public class departmentsControlleer implements departmentsRepo {


    private final departmentsService departmentsService;

    public departmentsControlleer(departmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @Override
    @GetMapping()
    @Operation(summary = "Get all data from tblDepartments")
    public List<departments> getAll() {
        return departmentsService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get departments by id")
    public ResponseEntity<departments> getById(@Parameter(description = "departments id to find")@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(departmentsService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PostMapping("/save")
    @Operation(summary = "Endpoint to save or update new/exists department" )
    public ResponseEntity<departments> save(@Parameter(description = "json body with info about department") @RequestBody departments departments) {
        try {
            return ResponseEntity.ok(departmentsService.save(departments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PostMapping("/delete/{id}")
    @Operation(summary = "Endpoint to delete exists department by id")
    @ApiResponse(responseCode = "200", description = "department deteled")
    @ApiResponse(responseCode = "404", description = "department not found")
    public ResponseEntity<departments> delete(@Parameter(description = "department id to delete")@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(departmentsService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
