package com.pdg.histouric.service;

import com.pdg.histouric.model.History;
import org.springframework.stereotype.Service;

public interface HistoryService {
    History createHistory(History history);
}
