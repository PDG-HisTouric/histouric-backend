package com.pdg.histouric.config;

import com.pdg.histouric.api.*;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.security.authorization.AuthorityAuthorizationManager;

import java.util.Arrays;
import java.util.List;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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

        RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder =
                RequestMatcherDelegatingAuthorizationManager.builder();

        configureUnlockedEndpoints(introspector, managerBuilder);
        configureEndpointsForUserApi(introspector, managerBuilder);
        configureEndpointsForBicApi(introspector, managerBuilder);
        configureEndpointsForHistoryApi(introspector, managerBuilder);
        configureEndpointsForFirebaseStorageApi(introspector, managerBuilder);
        configureEndpointsForRouteApi(introspector, managerBuilder);

        AuthorizationManager<HttpServletRequest> manager = managerBuilder.build();
        return (authentication, object) -> manager.check(authentication, object.getRequest());
    }

    private void configureUnlockedEndpoints(HandlerMappingIntrospector introspector, RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher login = new MvcRequestMatcher(introspector, AuthAPI.ROOT_PATH + "/login");
        login.setMethod(HttpMethod.POST);
        managerBuilder.add(login, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher createUser = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH);
        createUser.setMethod(HttpMethod.POST);
        managerBuilder.add(createUser, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getBics = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH);
        getBics.setMethod(HttpMethod.GET);
        managerBuilder.add(getBics, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getBicById = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH + "/{id}");
        getBicById.setMethod(HttpMethod.GET);
        managerBuilder.add(getBicById, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getHistoryById = new MvcRequestMatcher(introspector, HistoryAPI.ROOT_PATH + "/{historyId}");
        getHistoryById.setMethod(HttpMethod.GET);
        managerBuilder.add(getHistoryById, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getBicsByNameOrNickname = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH + "/name/{nameOrNickname}");
        getBicsByNameOrNickname.setMethod(HttpMethod.GET);
        managerBuilder.add(getBicsByNameOrNickname, (authentication, object) -> new AuthorizationDecision(true));
        
        MvcRequestMatcher getHistoriesByTitle = new MvcRequestMatcher(introspector, HistoryAPI.ROOT_PATH + "/title/{historyTitle}");
        getHistoriesByTitle.setMethod(HttpMethod.GET);
        managerBuilder.add(getHistoriesByTitle, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getRouteById = new MvcRequestMatcher(introspector, RouteAPI.ROOT_PATH + "/{id}");
        getRouteById.setMethod(HttpMethod.GET);
        managerBuilder.add(getRouteById, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getRoutes = new MvcRequestMatcher(introspector, RouteAPI.ROOT_PATH);
        getRoutes.setMethod(HttpMethod.GET);
        managerBuilder.add(getRoutes, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getSimplifiedRoutes = new MvcRequestMatcher(introspector, RouteAPI.ROOT_PATH + "/search");
        getSimplifiedRoutes.setMethod(HttpMethod.GET);
        managerBuilder.add(getSimplifiedRoutes, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getHistories = new MvcRequestMatcher(introspector, HistoryAPI.ROOT_PATH);
        getHistories.setMethod(HttpMethod.GET);
        managerBuilder.add(getHistories, (authentication, object) -> new AuthorizationDecision(true));

        MvcRequestMatcher getDirection = new MvcRequestMatcher(introspector, RouteAPI.ROOT_PATH + "/directions");
        getDirection.setMethod(HttpMethod.GET);
        managerBuilder.add(getDirection, (authentication, object) -> new AuthorizationDecision(true));
    }

    private void configureEndpointsForUserApi(HandlerMappingIntrospector introspector,
                                              RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher getUsers = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH);
        getUsers.setMethod(HttpMethod.GET);
        managerBuilder.add(getUsers, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN"));

        MvcRequestMatcher getUserByNickname = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{nickname}");
        getUserByNickname.setMethod(HttpMethod.GET);
        managerBuilder.add(getUserByNickname, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));

        MvcRequestMatcher getUsersThatContainsNickname = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/all/{nickname}");
        getUsersThatContainsNickname.setMethod(HttpMethod.GET);
        managerBuilder.add(getUsersThatContainsNickname, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN"));

        MvcRequestMatcher updateUserById = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{userId}");
        updateUserById.setMethod(HttpMethod.PUT);
        managerBuilder.add(updateUserById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));

        MvcRequestMatcher deleteUserById = new MvcRequestMatcher(introspector, UserAPI.ROOT_PATH + "/{userId}");
        deleteUserById.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deleteUserById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER", "RESEARCHER"));
    }

    private void configureEndpointsForBicApi(HandlerMappingIntrospector introspector,
                                             RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher createBic = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH);
        createBic.setMethod(HttpMethod.POST);
        managerBuilder.add(createBic, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher updateBicById = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH + "/{bicId}");
        updateBicById.setMethod(HttpMethod.PUT);
        managerBuilder.add(updateBicById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher patchBicById = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH + "/{bicId}");
        patchBicById.setMethod(HttpMethod.PATCH);
        managerBuilder.add(patchBicById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher deleteBicById = new MvcRequestMatcher(introspector, BicAPI.ROOT_PATH + "/{bicId}");
        deleteBicById.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deleteBicById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));
    }

    private void configureEndpointsForHistoryApi(HandlerMappingIntrospector introspector,
                                                 RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher createHistory = new MvcRequestMatcher(introspector, HistoryAPI.ROOT_PATH);
        createHistory.setMethod(HttpMethod.POST);
        managerBuilder.add(createHistory, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher deleteHistoryById = new MvcRequestMatcher(introspector, HistoryAPI.ROOT_PATH + "/{historyId}");
        deleteHistoryById.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deleteHistoryById, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));
    }

    private void configureEndpointsForFirebaseStorageApi(HandlerMappingIntrospector introspector,
                                                         RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher uploadImages = new MvcRequestMatcher(introspector, FirebaseStorageAPI.ROOT_PATH + "/images");
        uploadImages.setMethod(HttpMethod.POST);
        managerBuilder.add(uploadImages, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher uploadVideos = new MvcRequestMatcher(introspector, FirebaseStorageAPI.ROOT_PATH + "/videos");
        uploadVideos.setMethod(HttpMethod.POST);
        managerBuilder.add(uploadVideos, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));

        MvcRequestMatcher uploadAudios = new MvcRequestMatcher(introspector, FirebaseStorageAPI.ROOT_PATH + "/audios");
        uploadAudios.setMethod(HttpMethod.POST);
        managerBuilder.add(uploadAudios, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "RESEARCHER"));
    }

    private void configureEndpointsForRouteApi(HandlerMappingIntrospector introspector,
                                               RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder) {
        MvcRequestMatcher createRoute = new MvcRequestMatcher(introspector, RouteAPI.ROOT_PATH);
        createRoute.setMethod(HttpMethod.POST);
        managerBuilder.add(createRoute, AuthorityAuthorizationManager.hasAnyAuthority("ADMIN", "TOURISM_MANAGER"));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        return request -> configuration;
    }
}
