package com.example.onefit.role.permission;
import com.example.onefit.common.service.GenericService;
import com.example.onefit.role.permission.dto.PermissionCreateDto;
import com.example.onefit.role.permission.dto.PermissionResponseDto;
import com.example.onefit.role.permission.dto.PermissionUpdateDto;
import com.example.onefit.role.permission.entity.Permission;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class PermissionService extends GenericService<Permission,UUID, PermissionResponseDto,PermissionCreateDto,PermissionUpdateDto> {

    private final PermissionRepository repository;
    private final Class<Permission>entityClass = Permission.class;
    private final PermissionDtoMapper mapper;



    @Override
    protected PermissionResponseDto internalCreate(PermissionCreateDto permissionCreateDto) {
        Permission permission = mapper.toEntity(permissionCreateDto);
        permission.setId(UUID.randomUUID());
        Permission saved = repository.save(permission);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected PermissionResponseDto internalUpdate(UUID uuid, PermissionUpdateDto permissionUpdateDto) {
        Permission permission = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Permission with id: %s not fount".formatted(uuid)));
        mapper.toEntity(permissionUpdateDto,permission);
        Permission saved = repository.save(permission);
        return mapper.toResponseDto(saved);
    }
}
