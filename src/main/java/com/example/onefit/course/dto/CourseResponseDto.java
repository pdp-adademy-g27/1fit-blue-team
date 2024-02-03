package com.example.onefit.course.dto;

import com.example.onefit.course.entity.CourseType;
import com.example.onefit.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {
    private UUID id;
    private String name;
    private String description;
    private UUID trainerId;
    private String contactPhone;
    private CourseType courseType;
    private LocalDateTime startDate;
    private Image image;
    private int durationTime;
}
