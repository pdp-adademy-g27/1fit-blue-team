package com.example.onefit.restrictions.entity;

import com.example.onefit.image.entity.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restrictions {
    @Id
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    private Image image;
}
