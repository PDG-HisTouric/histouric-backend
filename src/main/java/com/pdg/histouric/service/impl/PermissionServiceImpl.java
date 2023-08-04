package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.PermissionErrorCode;
import com.pdg.histouric.error.exception.PermissionError;
import com.pdg.histouric.error.exception.PermissionException;
import com.pdg.histouric.model.CRUDPermission;
import com.pdg.histouric.repository.PermissionRepository;
import com.pdg.histouric.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public CRUDPermission createPermission(CRUDPermission permission) {
        return (CRUDPermission) permissionRepository.findByName(permission.getName())
                .map(permissionFound -> {
                    throw new PermissionException(
                            HttpStatus.BAD_REQUEST,
                            new PermissionError(PermissionErrorCode.CODE_02, PermissionErrorCode.CODE_02.getMessage())
                    );
                })
                .orElseGet(() -> permissionRepository.save(permission));
    }
}
