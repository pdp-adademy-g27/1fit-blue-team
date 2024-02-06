package com.example.onefit.config.security;


import com.example.onefit.security.JwtService;
import com.example.onefit.user.entity.User;
import com.example.onefit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Optional<User> optUser = userRepository.findByEmail(String.valueOf(attributes.get("email")));
        if (optUser.isPresent()) {
            UserPrincipal userPrincipal = UserPrincipal.create(optUser.get(), oAuth2User);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getAuthorities())
                    );
            return userPrincipal;
        }

        return UserPrincipal.create(new User(), oAuth2User);
    }
}
