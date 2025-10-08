package com.project.vms.vmsproject.repository;

import com.project.vms.vmsproject.model.VmsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<VmsUser, Long> {
    Optional<VmsUser> findByEmailAndPassword(String email, String password);
    List<VmsUser> findByName(String name);
}
