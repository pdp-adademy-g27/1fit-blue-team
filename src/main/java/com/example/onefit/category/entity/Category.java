package com.example.onefit.category.entity;

import com.example.onefit.gym.entity.Gym;
import com.example.onefit.image.entity.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    private UUID id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;
    @ManyToMany(mappedBy = "categories")
    private List<Gym> gyms;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children;
    @ManyToOne
    private Image image;
}