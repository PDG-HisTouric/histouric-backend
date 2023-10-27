package com.pdg.histouric.repository;

import com.pdg.histouric.model.History;
import com.pdg.histouric.model.HistoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryImageRepository extends JpaRepository<HistoryImage, UUID> {
    @Query("SELECT historyImage.history FROM HistoryImage historyImage WHERE historyImage.imageUri = ?1")
    List<History> findHistoriesByImageUri(String imageUri);
}
