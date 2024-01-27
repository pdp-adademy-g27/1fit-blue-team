package com.example.onefit.role.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Permission {
    @Id
    private UUID id;
    private String name;
}
