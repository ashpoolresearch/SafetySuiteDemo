package com.example.hr_api.controller;

import com.example.hr_api.entity.Employee;
import com.example.hr_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-org-unit/{orgUnitId}")
    public ResponseEntity<List<Employee>> getEmployeesByOrgUnit(@PathVariable Long orgUnitId) {
        return ResponseEntity.ok(employeeService.getEmployeesByOrgUnit(orgUnitId));
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<Employee> getEmployeeByUserId(@PathVariable String userId) {
        return employeeService.getEmployeeByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = new Employee(
                request.firstName, request.lastName, request.userId,
                request.position, request.dateOfBirth
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employee, request.organizationalUnitId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    public static class CreateEmployeeRequest {
        public String firstName;
        public String lastName;
        public String userId;
        public String position;
        public LocalDate dateOfBirth;
        public Long organizationalUnitId;
    }
}