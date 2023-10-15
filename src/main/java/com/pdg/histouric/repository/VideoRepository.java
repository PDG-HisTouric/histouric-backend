package com.pdg.histouric.repository;

import com.pdg.histouric.model.History;
import com.pdg.histouric.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {
    @Query("SELECT video.history FROM Video video WHERE video.videoUri = ?1")
    List<History> findHistoriesByVideoUri(String videoUri);
}
