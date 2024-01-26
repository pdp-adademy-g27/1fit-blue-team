package com.example.onefit.category.entity;

import com.example.onefit.gym.entity.Gym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`category_gym`")
public class CategoryGym {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID categoryId;
    private UUID gymId;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "gymId", insertable = false, updatable = false)
    private Gym gym;
}
