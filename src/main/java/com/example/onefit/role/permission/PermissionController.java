package com.example.onefit.role.permission;

import com.example.onefit.role.permission.dto.PermissionCreateDto;
import com.example.onefit.role.permission.dto.PermissionResponseDto;
import com.example.onefit.role.permission.dto.PermissionUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionResponseDto> createPermission(@RequestBody @Valid PermissionCreateDto permissionCreateDto){
        PermissionResponseDto permissionResponseDto = permissionService.create(permissionCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(permissionResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<PermissionResponseDto>>getAllPermission(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<PermissionResponseDto> permissionResponseDtoPage = permissionService.getAll(predicate, pageable);
        return ResponseEntity.ok(permissionResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDto>getPermission(@PathVariable UUID id){
        PermissionResponseDto permissionResponseDto = permissionService.get(id);
        return ResponseEntity.ok(permissionResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDto>updatePermission(@PathVariable UUID id, @RequestBody PermissionUpdateDto permissionUpdateDto){
        PermissionResponseDto update = permissionService.update(id, permissionUpdateDto);
        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable UUID id){
        permissionService.delete(id);
    }

}
