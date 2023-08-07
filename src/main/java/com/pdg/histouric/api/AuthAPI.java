package com.pdg.histouric.api;

import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserForLoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthAPI {

    String ROOT_PATH = "/api/v1/auth";

    @PostMapping("/login")
    TokenDTO login(@RequestBody UserForLoginDTO userForLoginDTO);


}
