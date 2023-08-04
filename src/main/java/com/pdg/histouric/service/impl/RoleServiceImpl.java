package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.RoleErrorCode;
import com.pdg.histouric.error.exception.RoleError;
import com.pdg.histouric.error.exception.RoleException;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.RoleRepository;
import com.pdg.histouric.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return (Role) roleRepository.findByName(role.getName())
                .map(roleFound -> {
                    throw new RoleException(
                            HttpStatus.BAD_REQUEST,
                            new RoleError(RoleErrorCode.CODE_02, RoleErrorCode.CODE_02.getMessage())
                    );
                })
                .orElseGet(() -> roleRepository.save(role));
    }
}
