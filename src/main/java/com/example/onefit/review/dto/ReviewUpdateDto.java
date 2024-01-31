package com.example.onefit.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateDto {
    private double cleanliness;
    private double staff;
    private double equipment;
    private double service;
    private String comment;
}
