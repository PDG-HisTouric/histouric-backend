package com.pdg.histouric.repository;

import com.pdg.histouric.model.RouteBICHistory;
import com.pdg.histouric.model.RouteBICHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RouteBICHistoryRepository extends JpaRepository<RouteBICHistory, RouteBICHistoryPK> {
    List<RouteBICHistory> findAllById_RouteId_OrderByBicOrderAsc(UUID routeId);
}
