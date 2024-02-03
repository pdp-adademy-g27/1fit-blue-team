package com.example.onefit.review.entity;

import com.example.onefit.course.entity.Course;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    private UUID id;
    private double cleanliness;
    private double staff;
    private double equipment;
    private double service;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "userId", updatable = false)
    private User author;
    private boolean isChanged;
    private LocalDateTime publishedAt;
    @ManyToOne(optional = false)
    private Course course;
}
