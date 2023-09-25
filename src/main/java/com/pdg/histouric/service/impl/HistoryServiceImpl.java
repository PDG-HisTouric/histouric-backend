package com.pdg.histouric.service.impl;

import com.pdg.histouric.model.History;
import com.pdg.histouric.service.HistoryService;
import com.pdg.histouric.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    @Override
    public History createHistory(History history) {
        return historyRepository.save(history);
    }
}
