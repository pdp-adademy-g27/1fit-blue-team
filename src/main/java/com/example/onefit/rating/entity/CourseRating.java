package com.example.onefit.rating.entity;

import com.example.onefit.course.entity.Course;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`course_rating`")
public class CourseRating {
    @Id
    private UUID ratingId;
    private UUID courseId;
    private UUID userId;
    private int userMark;
    private String description;
    private double avgMark;
    @OneToOne(mappedBy = "courseRating")
    private Course courseRating;
    @ManyToOne
    @JoinColumn(name="courseId")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

}
