package com.example.onefit.config.security;

import com.example.onefit.security.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2UserService oauth2UserService;
    private final AuthEntryPoint authEntryPoint;
    private final JwtAuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/v1/**").authenticated()
                            .anyRequest().permitAll();
                })
                .oauth2Login((oauth2) -> {
                    oauth2
                            .userInfoEndpoint(userInfoEndpointConfig -> {
                                userInfoEndpointConfig
                                        .userService(oauth2UserService);
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
                            .successHandler(oauth2SuccessHandler);
                })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
