package com.pdg.histouric.controller;

import com.pdg.histouric.api.RoleAPI;
import com.pdg.histouric.dto.RoleDTO;
import com.pdg.histouric.mapper.RoleMapper;
import com.pdg.histouric.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Override
    public RoleDTO createRole(@Valid RoleDTO roleDTO) {
        return roleMapper.fromRole(roleService.createRole(roleMapper.fromDTO(roleDTO)));
    }
}
