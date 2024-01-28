package com.example.onefit.gym;

import com.example.onefit.category.CategoryRepository;
import com.example.onefit.category.entity.Category;
import com.example.onefit.common.service.GenericService;
import com.example.onefit.feature.FeatureRepository;
import com.example.onefit.feature.entity.Feature;
import com.example.onefit.gym.dto.GymDto;
import com.example.onefit.gym.dto.GymResponseDto;
import com.example.onefit.gym.entity.Gym;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class GymService extends GenericService<Gym, UUID, GymResponseDto, GymDto, GymDto> {
    private final GymRepository repository;
    private final CategoryRepository categoryRepository;
    private final FeatureRepository featureRepository;
    private final Class<Gym> entityClass = Gym.class;
    private final GymDtoMapper mapper;

    @Override
    protected GymResponseDto internalCreate(GymDto createDto) {
        Gym gym = mapper.toEntity(createDto);

        setCategories(createDto, gym);
        setFeatures(createDto, gym);

        return mapper.toResponseDto(repository.save(gym));
    }


    @Override
    protected GymResponseDto internalUpdate(UUID id, GymDto gymDto) {
        Gym gym = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(gymDto, gym);
        setCategories(gymDto, gym);
        setFeatures(gymDto, gym);
        return mapper.toResponseDto(repository.save(gym));
    }


    private void setFeatures(GymDto gymDto, Gym gym) {
        List<Feature> features = gymDto.getFeatureIds()
                .stream()
                .map((fetId) -> featureRepository.findById(fetId)
                        .orElseThrow(EntityNotFoundException::new)
                ).collect(Collectors.toList());
        gym.setFeatures(features);
    }

    private void setCategories(GymDto gymDto, Gym gym) {
        List<Category> categories = gymDto.getCategoryIds()
                .stream()
                .map((catId) -> categoryRepository.findById(catId)
                        .orElseThrow(EntityNotFoundException::new)
                ).collect(Collectors.toList());
        gym.setCategories(categories);
    }
}
