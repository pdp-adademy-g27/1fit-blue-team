package com.example.onefit.feature;

import com.example.onefit.feature.dto.FeatureCreateDto;
import com.example.onefit.feature.dto.FeatureResponseDto;
import com.example.onefit.feature.dto.FeatureUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature")
public class FeatureController {
    private final FeatureService featureService;

    @PostMapping("/create")
    public ResponseEntity<FeatureResponseDto> create(@RequestBody @Valid FeatureCreateDto createDto){
        FeatureResponseDto featureResponseDto = featureService.create(createDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(featureResponseDto);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<FeatureResponseDto> update(
            @PathVariable UUID uuid,
            @RequestBody @Valid FeatureUpdateDto featureUpdateDto
    ) {
        FeatureResponseDto featureResponseDto = featureService.update(uuid, featureUpdateDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(featureResponseDto);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        featureService.delete(uuid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<FeatureResponseDto>> getAll(
            @RequestParam(required = false)
            String predicate,
            Pageable pageable) {
        Page<FeatureResponseDto> featureResponseDto = featureService.getAll(predicate, pageable);
        return ResponseEntity.ok(featureResponseDto);

    }
    @GetMapping("/{uuid}")
    public ResponseEntity<FeatureResponseDto> get(@PathVariable UUID uuid) {
        FeatureResponseDto featureResponseDto = featureService.getById(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(featureResponseDto);
    }



}
