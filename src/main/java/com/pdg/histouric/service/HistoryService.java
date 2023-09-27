package com.pdg.histouric.service;

import com.pdg.histouric.model.History;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    History createHistory(History history);
    void deleteHistory(UUID historyId);
    List<History> getHistoriesByImageUri(String imageUri);
    List<History> getHistoriesByVideoUrl(String videoUrl);
    History updateHistory(History history);
}
