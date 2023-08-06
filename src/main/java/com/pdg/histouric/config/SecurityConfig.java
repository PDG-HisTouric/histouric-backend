package com.pdg.histouric.config;

import com.pdg.histouric.api.AuthApi;
import com.pdg.histouric.api.UserAPI;
import com.pdg.histouric.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.security.authorization.AuthorityAuthorizationManager;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//Adapted from https://github.com/irojascorsico/spring-boot-jwt-authentication/tree/v1.0
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationManager<RequestAuthorizationContext> access) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(access)
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthorizationManager<RequestAuthorizationContext> requestMatcherAuthorizationManager
            (HandlerMappingIntrospector introspector) {

        RequestMatcher permitAll = getUnlockedEndpoints(introspector);
        RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder =
                RequestMatcherDelegatingAuthorizationManager.builder()
                        .add(permitAll, (context, other) -> new AuthorizationDecision(true));

        configureEndpointsForUserApi(introspector, managerBuilder);

        AuthorizationManager<HttpServletRequest> manager = managerBuilder.build();
        return (authentication, object) -> manager.check(authentication,object.getRequest());
    }

    private RequestMatcher getUnlockedEndpoints(HandlerMappingIntrospector introspector){
        MvcRequestMatcher login = new MvcRequestMatcher(introspector, AuthApi.ROOT_PATH + "/login");
        login.setMethod(HttpMethod.POST);

        MvcRequestMatcher createUser = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH);
        createUser.setMethod(HttpMethod.POST);

        return new AndRequestMatcher(login, createUser);
    }

    private void configureEndpointsForUserApi(HandlerMappingIntrospector introspector,
                                              RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder){
        MvcRequestMatcher getUsers = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH);
        getUsers.setMethod(HttpMethod.GET);
        managerBuilder.add(getUsers, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN"));

        MvcRequestMatcher getUserByUsername = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{username}");
        getUserByUsername.setMethod(HttpMethod.GET);
        managerBuilder.add(getUserByUsername, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));

        MvcRequestMatcher updateUserById = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{userId}");
        updateUserById.setMethod(HttpMethod.POST);
        managerBuilder.add(updateUserById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));

        MvcRequestMatcher deleteUserById = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{userId}");
        deleteUserById.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deleteUserById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));
    }
}
