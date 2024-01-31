package com.example.onefit.course.dto;

import com.example.onefit.category.entity.Category;
import com.example.onefit.course.entity.CourseType;
import com.example.onefit.gym.entity.Gym;
import com.example.onefit.restrictions.entity.Restrictions;
import com.example.onefit.review.entity.Review;
import com.example.onefit.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCreateDto {
    private User trainer;
    private CourseType courseType;
    private String contactPhone;
    private Category category;
    private Gym gym;
    private List<Restrictions> restrictions;
    private List<Review> reviews;

}
