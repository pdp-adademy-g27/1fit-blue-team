package com.example.onefit.feature.entity;

import com.example.onefit.gym.entity.Gym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gym_feature")
public class GymFeature {
    @Id
    private UUID featureId;
    private UUID gymId;

    @ManyToOne
    @JoinColumn(name = "featureId", insertable = false, updatable = false)
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "gymId", insertable = false, updatable = false)
    private Gym gym;
}