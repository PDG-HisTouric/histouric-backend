package com.pdg.histouric.repository;

import com.pdg.histouric.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, UUID> {
}
