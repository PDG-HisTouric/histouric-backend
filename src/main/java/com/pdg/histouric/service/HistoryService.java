package com.pdg.histouric.service;

import com.pdg.histouric.model.History;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    History createHistory(History history);
    void deleteHistory(UUID historyId);
    List<History> getHistoriesByImageUri(String imageUri);
    List<History> getHistoriesByVideoUri(String videoUrl);
    History updateHistory(History history);
    History getHistoryById(UUID historyId);
    List<History> getHistoriesByTitle(String historyTitle);
}
