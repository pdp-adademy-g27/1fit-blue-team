package com.example.onefit.course.entity;

import com.example.onefit.category.entity.Category;
import com.example.onefit.gym.entity.Gym;
import com.example.onefit.image.entity.Image;
import com.example.onefit.restrictions.entity.Restrictions;
import com.example.onefit.review.entity.Review;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {
    @Id
    private UUID id;

    private Integer totalVisits;

    private String name;

    private String description;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "trainer_id")
    private User trainer;

    private LocalDateTime startDate;

    private int durationTime;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    private String contactPhone;

    @ManyToMany
    @JoinTable(name = "user_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> registeredUsers;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
    @ManyToOne(optional = false)
    @JoinColumn(name = "gym_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Gym gym;

    @ManyToMany
    private List<Restrictions> restrictions;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews;

    @CreatedDate
    private LocalDateTime created;

    @ManyToOne
    private Image image;

}
