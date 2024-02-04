package com.example.onefit.feature;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.feature.entity.Feature;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends GenericRepository<Feature, UUID> {
}
