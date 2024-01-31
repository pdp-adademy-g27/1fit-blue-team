package com.example.onefit.role;

import com.example.onefit.role.dto.RoleCreateDto;
import com.example.onefit.role.dto.RoleResponseDto;
import com.example.onefit.role.dto.RoleUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody @Valid RoleCreateDto roleCreateDto){
        RoleResponseDto roleResponseDto = roleService.create(roleCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<RoleResponseDto>>roleGetAll(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<RoleResponseDto> roleResponseDtoPage = roleService.getAll(predicate, pageable);
        return ResponseEntity.ok(roleResponseDtoPage);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleResponseDto>getByName(@PathVariable String name){
        RoleResponseDto roleResponseDto = roleService.getByName(name);
        return ResponseEntity.ok(roleResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto>update(@PathVariable UUID id, @RequestBody RoleUpdateDto updateDto){
        RoleResponseDto update = roleService.update(id, updateDto);
        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        roleService.delete(id);
    }

    @PostMapping( ("/{roleId}/add-gym/{gymId}"))
    public ResponseEntity<RoleResponseDto> addGymToRole(@PathVariable UUID roleId,
                                                             @PathVariable UUID gymId) {
        RoleResponseDto roleResponseDto = roleService.addGym(roleId, gymId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleResponseDto);

    }
    }
