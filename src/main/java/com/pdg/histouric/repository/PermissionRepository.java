package com.pdg.histouric.repository;

import com.pdg.histouric.model.CRUDPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<CRUDPermission, UUID> {
    Optional<CRUDPermission> findByName(String name);

}
