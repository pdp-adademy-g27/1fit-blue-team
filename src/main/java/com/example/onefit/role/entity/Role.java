package com.example.onefit.role.entity;

import com.example.onefit.gym.entity.Gym;
import com.example.onefit.role.permission.entity.Permission;
import com.example.onefit.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    private UUID id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Gym gym;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
