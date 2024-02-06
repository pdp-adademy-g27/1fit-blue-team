package com.example.onefit.config.security;


import com.example.onefit.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserPrincipal implements UserDetails, OAuth2User {
    private UUID id;
    private String password;
    private User user;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    private String username;
    private boolean accountNonExpired = false;
    private boolean accountNonLocked = false;
    private boolean credentialsNonExpired = false;
    private boolean enabled = true;

    private UserPrincipal(User user, DefaultOAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        user.setName(String.valueOf(attributes.get("given_name")));
        user.setSurname(String.valueOf(attributes.get("family_name")));
        user.setEmail(String.valueOf(attributes.get("email")));
        this.password = user.getPassword();
        this.user = user;
        this.id = user.getId();
        this.authorities = user.getAuthorities();
        this.username = oAuth2User.getAttribute("sub");
        this.attributes = attributes;
    }

    public static UserPrincipal create(User user, DefaultOAuth2User oAuth2User) {
        return new UserPrincipal(user, oAuth2User);
    }

    public void setUser(User user) {
        this.password = user.getPassword();
        this.user = user;
    }

    @Override
    public String getName() {
        return "name";
    }


}
