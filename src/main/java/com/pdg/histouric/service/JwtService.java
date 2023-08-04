package com.pdg.histouric.service;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails user);

    String getUsernameFromToken(String token);

    boolean isTheTokenValid(String token, UserDetails userDetails);
}
