package com.pdg.histouric.security;

import com.pdg.histouric.model.HistouricUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

public class HistouricSecurityContext {
    public static UUID getCurrentUserId(){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)(SecurityContextHolder.getContext().getAuthentication());
        HistouricUser histouricUser = (HistouricUser) authenticationToken.getPrincipal();
        return histouricUser.getId();
    }

    public static List<String> getCurrentUserRole() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)(SecurityContextHolder.getContext().getAuthentication());
        return authenticationToken.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
    }
}
