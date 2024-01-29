package com.example.onefit.role.permission.entity;


import com.example.onefit.role.entity.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "permissions")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;
}
