package com.example.hr_api.repository

import com.example.hr_api.entity.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Long> {
    List<OrganizationalUnit> findByParentIsNull();
    List<OrganizationalUnit> findByParentId(Long parentId);
}