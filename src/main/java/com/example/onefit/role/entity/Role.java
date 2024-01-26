package com.example.onefit.role.entity;

import com.example.onefit.role.gym.entity.GymRole;
import com.example.onefit.user.role.entity.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`role`")
public class Role {
    @Id
    private UUID roleId;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<GymRole> gymRoles = new HashSet<>();
}
