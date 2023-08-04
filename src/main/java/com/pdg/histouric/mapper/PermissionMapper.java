package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.PermissionDTO;
import com.pdg.histouric.model.CRUDPermission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDTO fromCRUDPermission(CRUDPermission crudPermission);

    CRUDPermission fromDTO(PermissionDTO permissionDTO);
}
