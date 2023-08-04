package com.pdg.histouric.controller;

import com.pdg.histouric.api.PermissionApi;
import com.pdg.histouric.dto.PermissionDTO;
import com.pdg.histouric.mapper.PermissionMapper;
import com.pdg.histouric.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PermissionController implements PermissionApi {

    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionDTO createPermission(@Valid PermissionDTO permissionDTO) {
        return permissionMapper.fromCRUDPermission(permissionService.createPermission(permissionMapper.fromDTO(permissionDTO)));
    }
}
