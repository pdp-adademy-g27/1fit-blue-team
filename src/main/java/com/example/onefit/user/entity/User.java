package com.example.onefit.user.entity;

import com.example.onefit.image.entity.Image;
import com.example.onefit.role.entity.Role;
import com.example.onefit.role.permission.entity.Permission;
import com.example.onefit.subscription.entity.Subscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany
    @JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends;
    @ManyToMany
    private List<Role> roles;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @ManyToOne
    private Image image;

    @ManyToOne
    private Subscription subscription;

    private LocalDateTime activationDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.nonNull(roles)) {
            Stream<Permission> rolePermissionStream = roles.stream()
                    .map(Role::getPermissions)
                    .flatMap(Collection::stream);
            Stream<GrantedAuthority> roleAndPermissionStream = Stream.concat(rolePermissionStream, roles.stream());
            return roleAndPermissionStream.collect(Collectors.toSet());
        }
        return new ArrayList<>();
    }
    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
