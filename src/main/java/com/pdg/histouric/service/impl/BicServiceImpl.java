package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.BicErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.BicError;
import com.pdg.histouric.error.exception.BicException;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.BIC;
import com.pdg.histouric.repository.BicRepository;
import com.pdg.histouric.service.BicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.pdg.histouric.security.HistouricSecurityContext.getCurrentUserRoles;

@Service
@AllArgsConstructor
public class BicServiceImpl implements BicService {

    private final BicRepository bicRepository;

    @Override
    public BIC createBIC(BIC bic) {
        if (isUserResearcher()) {
            if (verifyBicExists(bic)) {
                throw new BicException(HttpStatus.BAD_REQUEST, new BicError(BicErrorCode.CODE_02, BicErrorCode.CODE_02.getMessage()));
            }
            return bicRepository.save(bic);
        }
        throw new UserException(HttpStatus.FORBIDDEN, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage()));
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

    private boolean isUserResearcher() {
        return getCurrentUserRoles().contains("RESEARCHER");
    }
}
