package com.example.onefit.gym.entity;

import com.example.onefit.category.entity.CategoryGym;
import com.example.onefit.feature.entity.GymFeature;
import com.example.onefit.role.gym.entity.GymRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`gym`")
public class Gym {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "gym")
    private Set<GymRole> gymRoles = new HashSet<>();

    @OneToMany(mappedBy = "gym")
    private List<GymFeature> gymFeatures = new ArrayList<>();


    @OneToMany(mappedBy = "gym")
    private List<CategoryGym> categoryGyms = new ArrayList<>();
}
