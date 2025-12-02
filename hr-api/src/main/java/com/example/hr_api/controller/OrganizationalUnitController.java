package com.example.hr_api.controller;

import com.example.hr_api.entity.OrganizationalUnit;
import com.example.hr_api.service.OrganizationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/org-units")
public class OrganizationalUnitController {

    private final OrganizationalUnitService orgUnitService;

    @Autowired
    public OrganizationalUnitController(OrganizationalUnitService orgUnitService) {
        this.orgUnitService = orgUnitService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationalUnit>> getAllOrganizationalUnits() {
        return ResponseEntity.ok(orgUnitService.getAllOrganizationalUnits());
    }

    @GetMapping("/tree")
    public ResponseEntity<List<OrganizationalUnit>> getOrganizationalUnitsTree() {
        return ResponseEntity.ok(orgUnitService.getRootOrganizationalUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationalUnit> getOrganizationalUnitById(@PathVariable Long id) {
        return orgUnitService.getOrganizationalUnitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrganizationalUnit> createOrganizationalUnit(@RequestBody CreateOrgUnitRequest request) {
        OrganizationalUnit created = orgUnitService.createOrganizationalUnit(request.description, request.parentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizationalUnit(@PathVariable Long id) {
        return orgUnitService.deleteOrganizationalUnit(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    public static class CreateOrgUnitRequest {
        public String description;
        public Long parentId;
    }
}