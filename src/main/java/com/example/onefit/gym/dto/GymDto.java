package com.example.onefit.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymDto {
    private String name;
    private String description;
    private String address;
    private Set<UUID> categoryIds;
    private Set<UUID> featureIds;
    private UUID imageId;
}
