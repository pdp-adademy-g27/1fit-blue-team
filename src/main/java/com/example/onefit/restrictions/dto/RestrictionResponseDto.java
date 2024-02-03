package com.example.onefit.restrictions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrictionResponseDto {
    private UUID id;
    private String name;
    private String description;
}
