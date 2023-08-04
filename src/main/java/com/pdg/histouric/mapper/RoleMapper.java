package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.RoleDTO;
import com.pdg.histouric.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromDTO(RoleDTO roleDTO);
    RoleDTO fromRole(Role role);
}
