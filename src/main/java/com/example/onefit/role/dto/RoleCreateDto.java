package com.example.onefit.role.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto {
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotEmpty(message = "Permissions cannot be empty")
    private Set<String> permissions;
}
