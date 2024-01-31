package com.example.onefit.role;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.role.dto.RoleCreateDto;
import com.example.onefit.role.dto.RoleResponseDto;
import com.example.onefit.role.dto.RoleUpdateDto;
import com.example.onefit.role.entity.Role;
import com.example.onefit.role.permission.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleDtoMapper extends GenericMapper<Role, RoleCreateDto,RoleResponseDto,RoleUpdateDto> {
    private final ModelMapper modelMapper;

    @Override
    public Role toEntity(RoleCreateDto roleCreateDto) {
        return modelMapper.map(roleCreateDto, Role.class);
    }

    @Override
    public RoleResponseDto toResponseDto(Role role) {
        RoleResponseDto response = modelMapper.map(role, RoleResponseDto.class);
        Set<String> permissions = role
                .getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
        response.setPermissions(permissions);

        return response;
    }

    @Override
    public void toEntity(RoleUpdateDto roleUpdateDto, Role role) {
        modelMapper.map(roleUpdateDto, role);
    }
}