package com.example.hr_api.repository;

import com.example.hr_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(String userId);
    List<Employee> findByOrganizationalUnitId(Long orgUnitId);
}