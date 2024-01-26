package com.example.onefit.course.entity;

import com.example.onefit.restrictions.entity.Restrictions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.persister.collection.mutation.RowMutationOperations;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`course_restrictions`")
public class CourseRestrictions {
    @Id
    private UUID courseId;
    private UUID restrictionId;

    @ManyToOne
    @JoinColumn(name = "courseId", insertable = false, updatable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "restrictionId", insertable = false, updatable = false)
    private Restrictions restrictions;
}
