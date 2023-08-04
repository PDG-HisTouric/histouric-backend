package com.pdg.histouric.api;

import com.pdg.histouric.dto.PermissionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/permissions")
public interface PermissionApi {
    String ROOT_PATH = "/api/v1/permissions";
    @PostMapping
    PermissionDTO createPermission(@RequestBody PermissionDTO permissionDTO);
}