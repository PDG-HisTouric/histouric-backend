package com.pdg.histouric.api;

import com.pdg.histouric.dto.CreateHistoryDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/histories")
public interface HistoryAPI {
    String ROOT_PATH = "/api/v1/histories";

    @PostMapping
    CreateHistoryDTO createHistory(@RequestBody CreateHistoryDTO createHistoryDTO) throws IOException;

    @DeleteMapping("/{historyId}")
    void deleteHistory(@PathVariable UUID historyId);

    @GetMapping("/image/{imageUri}")
    List<CreateHistoryDTO> getHistoriesByImageUri(@PathVariable String imageUri);

    @GetMapping("/video/{videoUrl}")
    List<CreateHistoryDTO> getHistoriesByVideoUri(@PathVariable String videoUrl);

    @PutMapping
    CreateHistoryDTO updateHistory(@RequestBody CreateHistoryDTO createHistoryDTO);
}
