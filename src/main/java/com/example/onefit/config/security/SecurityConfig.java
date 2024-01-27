package com.example.onefit.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login((oauth2) -> {
                    oauth2
                            .userInfoEndpoint(userInfoEndpointConfig -> {
                                userInfoEndpointConfig
                                        .userService(new DefaultOAuth2UserService());
                            })
                            .authorizationEndpoint(authorizationEndpointConfig -> {
                                authorizationEndpointConfig
                                        .baseUri("/oauth2/authorize")
                                        .authorizationRequestRepository(new HttpSessionOAuth2AuthorizationRequestRepository());
                            })
                            .redirectionEndpoint(redirectionEndpointConfig -> {
                                redirectionEndpointConfig.baseUri("/oauth2/callback/*");
                            })
                            .failureHandler(new AuthenticationEntryPointFailureHandler(authEntryPoint))
                            .successHandler(new ForwardAuthenticationSuccessHandler("/main-page"));
                })
                .build();
    }
}
