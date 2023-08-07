package com.pdg.histouric.controller;

import com.pdg.histouric.api.AuthAPI;
import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserForLoginDTO;
import com.pdg.histouric.service.AuthService;
import com.pdg.histouric.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

//Adapted from https://github.com/irojascorsico/spring-boot-jwt-authentication/tree/v1.0
@RestController
@AllArgsConstructor
public class AuthController implements AuthAPI {

    private final AuthService authService;

    @Override
    public TokenDTO login(@Valid UserForLoginDTO userForLoginDTO) {
        return authService.login(userForLoginDTO);
    }
}
