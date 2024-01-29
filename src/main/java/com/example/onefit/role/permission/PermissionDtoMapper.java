package com.example.onefit.role.permission;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.role.permission.dto.PermissionCreateDto;
import com.example.onefit.role.permission.dto.PermissionResponseDto;
import com.example.onefit.role.permission.dto.PermissionUpdateDto;
import com.example.onefit.role.permission.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionDtoMapper extends GenericMapper<Permission, PermissionCreateDto, PermissionResponseDto, PermissionUpdateDto> {
    private final ModelMapper modelMapper;

    @Override
    public Permission toEntity(PermissionCreateDto permissionCreateDto) {
        return modelMapper.map(permissionCreateDto, Permission.class);
    }

    @Override
    public PermissionResponseDto toResponseDto(Permission permission) {
       return modelMapper.map(permission, PermissionResponseDto.class);
    }

    @Override
    public void toEntity(PermissionUpdateDto permissionUpdateDto, Permission permission) {
        modelMapper.map(permissionUpdateDto, permission);
    }
}