package com.example.onefit.gym.entity;

import com.example.onefit.category.entity.Category;
import com.example.onefit.course.entity.Course;
import com.example.onefit.feature.entity.Feature;
import com.example.onefit.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gym {
    @Id
    private UUID id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "gym_category",
            joinColumns = {@JoinColumn(name = "gym_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "gym")
    private List<Course> courses;

    @ManyToMany
    private List<Feature> features;
}
