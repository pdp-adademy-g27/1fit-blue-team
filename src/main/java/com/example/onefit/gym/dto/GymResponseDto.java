package com.example.onefit.gym.dto;

import com.example.onefit.category.dto.CategoryResponseDto;
import com.example.onefit.course.dto.CourseResponseDto;
import com.example.onefit.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private List<CategoryResponseDto> categories;
    private List<CourseResponseDto> courses;
}
