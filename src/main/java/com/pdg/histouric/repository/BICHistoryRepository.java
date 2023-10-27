package com.pdg.histouric.repository;

import com.pdg.histouric.model.BICHistory;
import com.pdg.histouric.model.BICHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BICHistoryRepository extends JpaRepository<BICHistory, BICHistoryPK> {
}
