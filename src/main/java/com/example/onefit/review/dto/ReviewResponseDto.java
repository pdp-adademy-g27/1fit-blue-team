package com.example.onefit.review.dto;

import com.example.onefit.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private UUID id;
    private double cleanliness;
    private double staff;
    private double equipment;
    private double service;
    private boolean isChanged;
    private String comment;
    private String author;
    private LocalDateTime publishedAt;

}
