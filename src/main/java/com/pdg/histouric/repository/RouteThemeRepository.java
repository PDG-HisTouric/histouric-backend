package com.pdg.histouric.repository;

import com.pdg.histouric.model.RouteTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteThemeRepository extends JpaRepository<RouteTheme, UUID> {
    Optional<RouteTheme> findByName(String name);
}
