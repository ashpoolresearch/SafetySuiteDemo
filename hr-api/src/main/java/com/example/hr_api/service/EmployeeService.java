package com.example.hr_api.service;

import com.example.hr_api.entity.Employee;
import com.example.hr_api.repository.EmployeeRepository;
import com.example.hr_api.repository.OrganizationalUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OrganizationalUnitRepository orgUnitRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           OrganizationalUnitRepository orgUnitRepository) {
        this.employeeRepository = employeeRepository;
        this.orgUnitRepository = orgUnitRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmployeeByUserId(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    public List<Employee> getEmployeesByOrgUnit(Long orgUnitId) {
        return employeeRepository.findByOrganizationalUnitId(orgUnitId);
    }

    public Employee createEmployee(Employee employee, Long orgUnitId) {
        if (orgUnitId != null) {
            orgUnitRepository.findById(orgUnitId).ifPresent(employee::setOrganizationalUnit);
        }
        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}