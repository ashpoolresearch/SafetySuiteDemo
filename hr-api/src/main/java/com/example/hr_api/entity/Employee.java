package com.example.hr_api.entity;
import java.time.LocalDate;

import jdk.vm.ci.meta.Local;
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String userId;
    private String position;
    private LocalDate dateOfBirth;
    private OrganizationalUnit organizationalUnit;

    public Employee() {}

    public Employee(String firstName, String lastName, String userId, 
    String position, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public OrganizationalUnit getOrganizationalUnit() { return organizationalUnit; }
    public void setOrganizationalUnit(OrganizationalUnit organizationalUnit) { this.organizationalUnit = organizationalUnit; }

    public Long getOrganizationalUnitId() {
        return organizationalUnit != null ? organizationalUnit.getId() : null;
    }
}