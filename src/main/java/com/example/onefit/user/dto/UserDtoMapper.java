package com.example.onefit.user.dto;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper extends GenericMapper<User, UserCreateDto, UserResponseDto, UserUpdateDto> {
    private final ModelMapper modelMapper= new ModelMapper();

    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        return modelMapper.map(userCreateDto, User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = modelMapper.map(user, UserResponseDto.class);
        return responseDto;
    }

    @Override
    public void toEntity(UserUpdateDto userUpdateDto, User user) {
        modelMapper.map(userUpdateDto, user);
    }
}
