package com.pdg.histouric.service.impl;

import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserForLoginDTO;
import com.pdg.histouric.repository.UserRepository;
import com.pdg.histouric.service.AuthService;
import com.pdg.histouric.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public TokenDTO login(UserForLoginDTO userForLoginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForLoginDTO.getEmail(), userForLoginDTO.getPassword()));
        UserDetails user = userRepository.findByEmail(userForLoginDTO.getEmail()).orElseThrow();
        String token = jwtUtil.getToken(user);
        return TokenDTO.builder()
            .token(token)
            .build();
    }
}
