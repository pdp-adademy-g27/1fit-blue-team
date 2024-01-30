package com.example.onefit.feature.entity;

import com.example.onefit.image.entity.Image;
import jakarta.persistence.*;
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
public class Feature {
    @Id
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    private Image image;
}
