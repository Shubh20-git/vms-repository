package com.project.vms.vmsproject.repository;

import com.project.vms.vmsproject.model.VmsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmsRepo extends JpaRepository<VmsUser, Long> {
}
