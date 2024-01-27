package com.example.onefit.config.security;

import com.example.onefit.response.ApiResult;
import com.example.onefit.response.ErrorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint{
        @Override
        public void commence(HttpServletRequest request,
                             HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            ApiResult<ErrorData> apiResult = ApiResult.failResponse(authException.getMessage(), HttpStatus.UNAUTHORIZED.value());
            String errorMessage = new ObjectMapper().writeValueAsString(apiResult);
            response.getWriter().write(errorMessage);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
}
