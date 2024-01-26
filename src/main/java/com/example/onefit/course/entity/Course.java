package com.example.onefit.course.entity;

import com.example.onefit.gym.entity.Gym;
import com.example.onefit.rating.entity.CourseRating;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.onefit.review.entity.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`course`")
public class Course {
    @Id
    private UUID id;
    @OneToOne
    @JoinColumn(name = "reviewId")
    private Review review;
    private String name;
    private String description;
    private UUID trainerId;
    private LocalDateTime startDate;
    private int durationTime;
    private CourseType courseType;
    private String contactPhone;
    @OneToOne
    @JoinColumn(name = "trainerId")
    private User trainer;
    @ManyToOne
    @JoinColumn(name = "gymId", insertable = false, updatable = false)
    private Gym gym;
    private UUID reviewId;
    @OneToMany(mappedBy = "course")
    private List<CourseRating> courseRatings;
    @OneToMany(mappedBy = "course")
    private List<CourseRestrictions> courseRestrictions = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "reviewId")
    private CourseRating courseRating;
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();
}
