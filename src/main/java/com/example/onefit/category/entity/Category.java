package com.example.onefit.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<CategoryGym> categoryGyms = new ArrayList<>();
}