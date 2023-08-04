package com.pdg.histouric.api;

import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserForLoginDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @PostMapping("/login")
    TokenDTO login(@Valid @RequestBody UserForLoginDTO userForLoginDTO);


}
