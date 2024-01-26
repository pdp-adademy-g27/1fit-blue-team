package com.example.onefit.role.gym.entity;

import com.example.onefit.gym.entity.Gym;
import com.example.onefit.role.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`gym_role`")
public class GymRole {
    @Id
    private UUID gymId;
    private UUID roleId;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "gymId", insertable = false, updatable = false)
    private Gym gym;
}
