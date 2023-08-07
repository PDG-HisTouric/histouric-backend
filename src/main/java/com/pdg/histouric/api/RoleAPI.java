package com.pdg.histouric.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pdg.histouric.dto.RoleDTO;


@RequestMapping("/api/v1/roles")
public interface RoleAPI {
    String ROOT_PATH = "/api/v1/roles";
    @PostMapping
    RoleDTO createRole(@RequestBody RoleDTO roleDTO);

}
