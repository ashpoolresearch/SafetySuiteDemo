package com.example.hr_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizational_units")
public class OrganizationalUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private OrganizationalUnit parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<OrganizationalUnit> children = new ArrayList<>();

    public OrganizationalUnit() {}

    public OrganizationalUnit(String description) {
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public OrganizationalUnit getParent() { return parent; }
    public void setParent(OrganizationalUnit parent) { this.parent = parent; }

    public List<OrganizationalUnit> getChildren() { return children; }
    public void setChildren(List<OrganizationalUnit> children) { this.children = children; }

    public Long getParentId() {
        return parent != null ? parent.getId() : null;
    }
}