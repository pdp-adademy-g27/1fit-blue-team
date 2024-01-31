package com.example.onefit.gym.dto;

import com.example.onefit.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private Image image;
    // todo Add the rest of the fields when needed DTOs are created
}
