package com.example.onefit.course.entity;

import com.example.onefit.gym.entity.Gym;
import com.example.onefit.restrictions.entity.Restrictions;
import com.example.onefit.review.entity.Review;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "trainer_id")
    private User trainer;
    private LocalDate startDate;
    private int durationTime;
    @Enumerated(EnumType.STRING)
    private CourseType courseType;
    private String contactPhone;
    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Gym gym;
    @ManyToMany
    private List<Restrictions> restrictions;
    @OneToMany(mappedBy = "course")
    private List<Review> reviews;
}
