package com.pdg.histouric.api;

import com.pdg.histouric.dto.HistoryDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/histories")
public interface HistoryAPI {
    String ROOT_PATH = "/api/v1/histories";

    @PostMapping
    HistoryDTO createHistory(@RequestBody HistoryDTO historyDTO);
}
