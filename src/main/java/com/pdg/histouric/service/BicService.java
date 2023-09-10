package com.pdg.histouric.service;

import com.pdg.histouric.model.BIC;

import java.util.List;
import java.util.UUID;

public interface BicService {
    BIC createBIC(BIC createBicDTO);
    List<BIC> getBICs();
    BIC getBicById(UUID id);
    BIC updateBicById(UUID bicId, BIC bicDTO);
    void deleteBicById(UUID bicId);
}
