package com.project.vms.vmsproject.repository;

import com.project.vms.vmsproject.enums.CveSeverity;
import com.project.vms.vmsproject.enums.CveStatus;
import com.project.vms.vmsproject.model.VmsCve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CveRepository extends JpaRepository<VmsCve, Long> {
    Optional<VmsCve> findByCveId(String cveId);
    List<VmsCve> findByCveStatus(CveStatus cveStatus);
    List<VmsCve> findBySeverity(CveSeverity sv);
    List<VmsCve> findByDescriptionContainingIgnoreCase(String description);
}
