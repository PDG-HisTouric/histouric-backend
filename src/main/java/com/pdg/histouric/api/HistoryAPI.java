package com.pdg.histouric.api;

import com.pdg.histouric.dto.CreateHistoryDTO;
import com.pdg.histouric.dto.ResponseHistoryDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/histories")
public interface HistoryAPI {
    String ROOT_PATH = "/api/v1/histories";

    @PostMapping
    ResponseHistoryDTO createHistory(@RequestBody CreateHistoryDTO createHistoryDTO) throws IOException;
//    ResponseHistoryDTO createHistory() throws IOException;

    @DeleteMapping("/{historyId}")
    void deleteHistory(@PathVariable UUID historyId);

    @GetMapping("/image/{imageUri}")
    List<ResponseHistoryDTO> getHistoriesByImageUri(@PathVariable String imageUri);

    @GetMapping("/video/{videoUrl}")
    List<ResponseHistoryDTO> getHistoriesByVideoUri(@PathVariable String videoUrl);

    @PutMapping
    ResponseHistoryDTO updateHistory(@RequestBody CreateHistoryDTO createHistoryDTO);

    @GetMapping("/{historyId}")
    ResponseHistoryDTO getHistoryById(@PathVariable UUID historyId);
    
    @GetMapping("/title/{historyTitle}")
    List<ResponseHistoryDTO> getHistoriesByTitle(@PathVariable String historyTitle);
}
