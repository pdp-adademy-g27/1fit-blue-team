package com.example.onefit.feature;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.feature.dto.FeatureCreateDto;
import com.example.onefit.feature.dto.FeatureResponseDto;
import com.example.onefit.feature.dto.FeatureUpdateDto;
import com.example.onefit.feature.entity.Feature;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeatureMapperDto extends GenericMapper<Feature, FeatureCreateDto, FeatureResponseDto, FeatureUpdateDto> {
    private final ModelMapper modelMapper;
    @Override
    public Feature toEntity(FeatureCreateDto featureCreateDto) {
        return modelMapper.map(featureCreateDto, Feature.class);
    }

    @Override
    public FeatureResponseDto toResponseDto(Feature feature) {
        return modelMapper.map(feature, FeatureResponseDto.class);
    }

    @Override
    public void toEntity(FeatureUpdateDto featureUpdateDto, Feature feature) {
        modelMapper.map(featureUpdateDto, feature);
    }

}
