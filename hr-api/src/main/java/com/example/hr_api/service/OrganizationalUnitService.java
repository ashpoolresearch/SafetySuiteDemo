package com.example.hr_api.service;

import com.example.hr_api.entity.OrganizationalUnit;
import com.example.hr_api.repository.OrganizationalUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationalUnitService {

    private final OrganizationalUnitRepository orgUnitRepository;

    @Autowired
    public OrganizationalUnitService(OrganizationalUnitRepository orgUnitRepository) {
        this.orgUnitRepository = orgUnitRepository;
    }

    public List<OrganizationalUnit> getAllOrganizationalUnits() {
        return orgUnitRepository.findAll();
    }

    public List<OrganizationalUnit> getRootOrganizationalUnits() {
        return orgUnitRepository.findByParentIsNull();
    }

    public Optional<OrganizationalUnit> getOrganizationalUnitById(Long id) {
        return orgUnitRepository.findById(id);
    }

    public OrganizationalUnit createOrganizationalUnit(String description, Long parentId) {
        OrganizationalUnit orgUnit = new OrganizationalUnit(description);
        if (parentId != null) {
            orgUnitRepository.findById(parentId).ifPresent(orgUnit::setParent);
        }
        return orgUnitRepository.save(orgUnit);
    }

    public boolean deleteOrganizationalUnit(Long id) {
        if (orgUnitRepository.existsById(id)) {
            orgUnitRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
