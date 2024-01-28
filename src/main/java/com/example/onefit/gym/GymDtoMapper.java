package com.example.onefit.gym;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.gym.dto.GymDto;
import com.example.onefit.gym.dto.GymResponseDto;
import com.example.onefit.gym.entity.Gym;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GymDtoMapper extends GenericMapper<Gym, GymDto, GymResponseDto, GymDto> {
    private final ModelMapper modelMapper;
    @Override
    public Gym toEntity(GymDto gymCreateDto) {
        Gym gym = modelMapper.map(gymCreateDto, Gym.class);
        gym.setId(UUID.randomUUID());
        return gym;
    }

    @Override
    public GymResponseDto toResponseDto(Gym gym) {
        return modelMapper.map(gym, GymResponseDto.class);
    }

    @Override
    public void toEntity(GymDto gymDto, Gym gym) {
        modelMapper.map(gymDto, gym);
    }
}
