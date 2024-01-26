package com.example.onefit.user.entity;

import com.example.onefit.course.entity.Course;
import com.example.onefit.friend.entity.Friend;
import com.example.onefit.rating.entity.CourseRating;
import com.example.onefit.review.entity.Review;
import com.example.onefit.user.role.entity.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    private Gender gender;
    @OneToOne(mappedBy = "trainer")
    private Course course;
    @OneToMany(mappedBy = "user")
    private List<Friend> userFriends;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<CourseRating> courseRatings = new ArrayList<>();
}
