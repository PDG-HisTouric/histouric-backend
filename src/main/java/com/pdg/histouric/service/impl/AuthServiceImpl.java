package com.pdg.histouric.service.impl;

import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserDTO;
import com.pdg.histouric.dto.UserForLoginDTO;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.repository.UserRepository;
import com.pdg.histouric.service.AuthService;
import com.pdg.histouric.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public TokenDTO login(UserForLoginDTO userForLoginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForLoginDTO.getEmail(), userForLoginDTO.getPassword()));
        UserDetails user = userRepository.findByEmail(userForLoginDTO.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return TokenDTO.builder()
            .token(token)
            .build();
    }

    public TokenDTO register(UserDTO userDTO) {
        HistouricUser histouricUser = HistouricUser.builder()
            .id(UUID.randomUUID())
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .password(passwordEncoder.encode(userDTO.getPassword()))
            .roles(null)
            .build();

        userRepository.save(histouricUser);
        return TokenDTO.builder()
            .token(jwtService.getToken(histouricUser))
            .build();
    }
}
