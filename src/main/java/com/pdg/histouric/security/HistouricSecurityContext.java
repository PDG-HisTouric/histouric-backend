package com.pdg.histouric.security;

import com.pdg.histouric.model.HistouricUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HistouricSecurityContext {
    public static Optional<UUID> getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return Optional.empty();

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        HistouricUser histouricUser = (HistouricUser) authenticationToken.getPrincipal();
        return Optional.of(histouricUser.getId());
    }

    public static List<String> getCurrentUserRoles() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        return authenticationToken.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
    }
}
