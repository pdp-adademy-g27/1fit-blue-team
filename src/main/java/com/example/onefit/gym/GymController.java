package com.example.onefit.gym;

import com.example.onefit.common.AppConstants;
import com.example.onefit.gym.dto.GymDto;
import com.example.onefit.gym.dto.GymResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + GymController.BASE_URL)
@RequiredArgsConstructor
public class GymController {
    public static final String BASE_URL = "/gym";
    private final GymService service;

    @PostMapping
    public ResponseEntity<GymResponseDto> create(@RequestBody GymDto createDto) {
        return ResponseEntity.ok(service.internalCreate(createDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymResponseDto> update(@PathVariable UUID id, @RequestBody GymDto gymDto) {
        return ResponseEntity.ok(service.update(id, gymDto));
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<GymResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        return ResponseEntity.ok(service.getAll(predicate, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
