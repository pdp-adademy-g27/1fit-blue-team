package com.example.onefit.config.security;

import com.example.onefit.common.exceptions.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        RedirectStrategy redirectStrategy = getRedirectStrategy();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        request.setAttribute("googleUser", principal);
        if (response.isCommitted()) {
            throw ApiException.throwException("Response is already committed");
        }

        // if user is already registered, he has id
        if (Objects.nonNull(principal.getId()))
            redirectStrategy.sendRedirect(request, response, "/main-page");
        else
            redirectStrategy.sendRedirect(request, response, "/complete-login");

    }
}
