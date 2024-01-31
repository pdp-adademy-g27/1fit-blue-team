package com.example.onefit.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private String name;
    private String description;
    private LocalDate startDate;
    private int durationTime;
}
