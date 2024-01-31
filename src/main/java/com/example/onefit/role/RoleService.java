package com.example.onefit.role;

import com.example.onefit.common.service.GenericService;
import com.example.onefit.gym.GymRepository;
import com.example.onefit.gym.entity.Gym;
import com.example.onefit.role.dto.RoleCreateDto;
import com.example.onefit.role.dto.RoleResponseDto;
import com.example.onefit.role.dto.RoleUpdateDto;
import com.example.onefit.role.entity.Role;
import com.example.onefit.role.permission.PermissionRepository;
import com.example.onefit.role.permission.entity.Permission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class RoleService extends GenericService<Role, UUID, RoleResponseDto, RoleCreateDto, RoleUpdateDto> {

    private final RoleRepository repository;
    private final PermissionRepository permissionRepository;
    private final GymRepository gymRepository;
    private final Class<Role>entityClass= Role.class;
    private final RoleDtoMapper mapper;
    private final EntityManager entityManager;


    @Override
    @Transactional
    public RoleResponseDto internalCreate(RoleCreateDto roleCreateDto) {
        Role role = mapper.toEntity(roleCreateDto);

        Set<Permission> dtoPermissionNames = role.getPermissions();

        Set<Permission> permissions = permissionRepository.findAllByNameIn(roleCreateDto.getPermissions());
        if(dtoPermissionNames.size() != permissions.size()){
            Set<String> permissionNames = permissions
                    .stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet());

            dtoPermissionNames.removeAll(permissionNames);
            throw new EntityNotFoundException("Permissions with these names are not found.Permissions: %s "
                    .formatted(dtoPermissionNames));
        }
        role.setPermissions(permissions);
        role.setId(UUID.randomUUID());

        Role saved = repository.save(role);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected RoleResponseDto internalUpdate(UUID uuid, RoleUpdateDto roleUpdateDto) {
        Role role = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Role with id: %s not fount".formatted(uuid)));
        mapper.toEntity(roleUpdateDto,role);
        Role saved = repository.save(role);
        return mapper.toResponseDto(saved);
    }
    public RoleResponseDto getByName(String name){
        Role role = repository
                .findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Role with name: %s not found".formatted(name)));
        return mapper.toResponseDto(role);
    }
    @Transactional
    public RoleResponseDto addGym(UUID roleId, UUID gymId) {
        Role role = repository.findById(roleId).orElseThrow();
        Gym gym = gymRepository.findById(gymId).orElseThrow();

        role.setGym(gym);
        Role saved = repository.save(role);

       /* entityManager.flush();
        entityManager.clear();*/

        return mapper.toResponseDto(saved);
    }


}
