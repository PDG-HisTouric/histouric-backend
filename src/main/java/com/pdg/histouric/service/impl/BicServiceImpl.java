package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.BicErrorCode;
import com.pdg.histouric.constant.HistoryErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.*;
import com.pdg.histouric.model.BIC;
import com.pdg.histouric.model.BICHistory;
import com.pdg.histouric.model.BICHistoryPK;
import com.pdg.histouric.model.History;
import com.pdg.histouric.repository.*;
import com.pdg.histouric.service.BicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.pdg.histouric.security.HistouricSecurityContext.getCurrentUserRoles;

@Service
@AllArgsConstructor
public class BicServiceImpl implements BicService {

    private final BicRepository bicRepository;
    private final BICImageRepository bicImageRepository;
    private final NicknameRepository nicknameRepository;
    private final HistoryRepository historyRepository;
    private final BICHistoryRepository bicHistoryRepository;

    @Override
    public BIC createBIC(BIC bic) {
        if (isUserResearcher()) {
            if (verifyBicExists(bic)) {
                throw new BicException(HttpStatus.BAD_REQUEST, new BicError(BicErrorCode.CODE_02, BicErrorCode.CODE_02.getMessage()));
            }
            List<BICHistory> bicHistories = bic.getBicHistories();
            bic.setBicHistories(null);
            BIC createdBIC = bicRepository.save(bic);
            if (bic.getImages() != null) {
                bic.getImages().forEach(image -> {
                    image.setBic(createdBIC);
                    bicImageRepository.save(image);
                });
            }
            if (bic.getNicknames() != null) {
                bic.getNicknames().forEach(nickname -> {
                    nickname.setBic(createdBIC);
                    nicknameRepository.save(nickname);
                });
            }
            bicHistories.forEach(bicHistory -> associateHistoryAndBIC(createdBIC.getId(), bicHistory.getId().getHistoryId()));
            fillHistoriesOfBic(createdBIC, bicHistories);
            return createdBIC;
        }
        throw new UserException(HttpStatus.FORBIDDEN, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage()));
    }

    private void fillHistoriesOfBic(BIC bic, List<BICHistory> bicHistories) {
        bicHistories.forEach(bicHistory -> {
            History history = getHistoryById(bicHistory.getId().getHistoryId());
            bicHistory.setHistory(history);
            bicHistory.setBic(bic);
        });
        bic.setBicHistories(bicHistories);
    }

    private void associateHistoryAndBIC(UUID bicId, UUID historyId) {
        BICHistoryPK bicHistoryPK = BICHistoryPK.builder()
                .bicId(bicId)
                .historyId(historyId)
                .build();
        BICHistory bicHistory = BICHistory.builder()
                .id(bicHistoryPK)
                .build();
        bicHistoryRepository.save(bicHistory);
    }

    private History getHistoryById(UUID historyId) {
        return historyRepository.findById(historyId)
                .orElseThrow(() -> new HistoryException(HttpStatus.NOT_FOUND, new HistoryError(HistoryErrorCode.CODE_01, HistoryErrorCode.CODE_01.getMessage())));
    }

    private boolean verifyBicExists(BIC bic) {
        return bicRepository.findByLatitudeAndLongitudeAndExistss(bic.getLatitude(), bic.getLongitude(), bic.isExistss()).isPresent();
    }

    @Override
    public List<BIC> getBICs() {
        return bicRepository.findAll();
    }

    @Override
    public BIC getBicById(UUID id) {
        return bicRepository.findById(id).orElseThrow(() -> new BicException(HttpStatus.NOT_FOUND, new BicError(BicErrorCode.CODE_01, BicErrorCode.CODE_01.getMessage())));
    }

    @Override
    public BIC updateBicById(UUID bicId, BIC bicDTO) {
        if (isUserResearcher()) {
            return bicRepository.findById(bicId)
                    .map(bicFound -> {
                        bicFound.setName(bicDTO.getName());
                        bicFound.setDescription(bicDTO.getDescription());
                        bicFound.setLatitude(bicDTO.getLatitude());
                        bicFound.setLongitude(bicDTO.getLongitude());
                        bicFound.setExistss(bicDTO.isExistss());
                        return bicRepository.save(bicFound);
                    })
                    .orElseThrow(() -> new BicException(HttpStatus.NOT_FOUND, new BicError(BicErrorCode.CODE_01, BicErrorCode.CODE_01.getMessage())));
        }
        throw new UserException(HttpStatus.FORBIDDEN, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage()));
    }

    @Override
    public void deleteBicById(UUID bicId) {
        if (isUserResearcher()) {
            bicRepository.deleteById(bicId);
            return;
        }
        throw new UserException(HttpStatus.FORBIDDEN, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage()));
    }

    @Override
    public List<BIC> getBicByNameOrNickname(String nameOrNickname) {
        Optional<List<BIC>> bics = bicRepository.findByNameContainsIgnoreCase(nameOrNickname);
        Optional<List<BIC>> bicsByNickname = nicknameRepository.findByNicknameContainsIgnoreCase(nameOrNickname);
        Set<BIC> bicsFound = new HashSet<>();
        bics.ifPresent(bicsFound::addAll);
        bicsByNickname.ifPresent(bicsFound::addAll);
        return bicsFound.stream().toList();
    }

    private boolean isUserResearcher() {
        return getCurrentUserRoles().contains("RESEARCHER");
    }
}
