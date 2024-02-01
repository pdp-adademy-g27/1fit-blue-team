package com.example.onefit.feature;


import com.example.onefit.common.service.GenericService;
import com.example.onefit.feature.dto.FeatureCreateDto;
import com.example.onefit.feature.dto.FeatureResponseDto;
import com.example.onefit.feature.dto.FeatureUpdateDto;
import com.example.onefit.feature.entity.Feature;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class FeatureService extends GenericService<Feature, UUID, FeatureResponseDto, FeatureCreateDto, FeatureUpdateDto> {
    private final FeatureRepository repository;
    private final Class<Feature> entityClass = Feature.class;
    private final FeatureMapperDto mapper;

    @Override
    protected FeatureResponseDto internalCreate(FeatureCreateDto featureCreateDto) {
        Feature entity = mapper.toEntity(featureCreateDto);
        entity.setId(UUID.randomUUID());
        Feature saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected FeatureResponseDto internalUpdate(UUID uuid, FeatureUpdateDto featureUpdateDto) {
        Feature existingFeature = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found with id: " + uuid));

        mapper.toEntity(featureUpdateDto, existingFeature);
        Feature updatedFeature = repository.save(existingFeature);
        return mapper.toResponseDto(updatedFeature);
    }
    public void delete(UUID uuid) {
        Feature existingFeature = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found with id: " + uuid));

        repository.delete(existingFeature);
    }

    public FeatureResponseDto getById(UUID uuid) {
        Feature existingFeature = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found with id: " + uuid));

        return mapper.toResponseDto(existingFeature);
    }


}
