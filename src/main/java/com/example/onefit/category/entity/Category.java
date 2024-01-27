package com.example.onefit.category.entity;

import com.example.onefit.gym.entity.Gym;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private UUID id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;
    @ManyToMany(mappedBy = "categories")
    private List<Gym> gyms;
    @OneToMany
    private List<Category> children;
}