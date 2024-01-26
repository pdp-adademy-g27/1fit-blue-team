package com.example.onefit.review.entity;

import com.example.onefit.course.entity.Course;
import com.example.onefit.gym.entity.Gym;
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
@Table(name = "`review`")
public class Review {
    @Id
    private UUID id;
    private UUID userId;
    private int cleanliness;
    private int equipment;
    private int staff;
    private int service;
    private String comment;
    private LocalDateTime publishedAt;
    @ManyToOne
    @JoinColumn(name = "courseId", insertable = false, updatable = false)
    private Course course;
    @OneToOne(mappedBy = "review")
    private Gym gym;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;
}
