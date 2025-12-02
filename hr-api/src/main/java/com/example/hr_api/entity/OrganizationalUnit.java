package com.example.hr_api.entity;

import java.util.List;
import java.util.ArrayList;

public class OrganizationalUnit {
    private Long id;
    private String description;
    private OrganizationalUnit parent;
    private List<OrganizationalUnit> children = new ArrayList<>();


    public OrganizationalUnit() {}

    public OrganizationalUnit(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationalUnit getParent() {
        return parent;
    }

    public void setParent(OrganizationalUnit parent) {
        this.parent = parent;
    }

    public List<OrganizationalUnit> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationalUnit> children) {
        this.children=children;
    }

    public Long getParentId() {
        return parent != null ? parent.getId() : null;
    }
}