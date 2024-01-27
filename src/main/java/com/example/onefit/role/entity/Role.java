package com.example.onefit.role.entity;

import com.example.onefit.gym.entity.Gym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`role`")
public class Role {
    @Id
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id")
    private Gym gym;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
}
