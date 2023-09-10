package com.pdg.histouric.repository;

import com.pdg.histouric.model.BIC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BicRepository extends JpaRepository<BIC, UUID> {
    Optional<BIC> findByLatitudeAndLongitudeAndExistss(double latitude, double longitude, boolean existss);
}