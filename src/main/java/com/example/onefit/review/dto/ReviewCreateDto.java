package com.example.onefit.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateDto {
    private double cleanliness;
    private double staff;
    private double equipment;
    private double service;
    private String comment;
    private UUID userId;
    private UUID courseId;
}
