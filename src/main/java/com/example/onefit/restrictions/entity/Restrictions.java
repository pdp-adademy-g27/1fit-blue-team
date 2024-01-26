package com.example.onefit.restrictions.entity;

import com.example.onefit.course.entity.CourseRestrictions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`restrictions`")
public class Restrictions {
    @Id
    private UUID id;
    @OneToMany(mappedBy = "restrictions")
    private List<CourseRestrictions> courseRestrictions = new ArrayList<>();
}
