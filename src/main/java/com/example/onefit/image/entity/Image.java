package com.example.onefit.image.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String name;
    private String contentType;
    private long size;
}
