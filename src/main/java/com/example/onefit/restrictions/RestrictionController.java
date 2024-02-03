package com.example.onefit.restrictions;

import com.example.onefit.restrictions.dto.RestrictionCreateDto;
import com.example.onefit.restrictions.dto.RestrictionResponseDto;
import com.example.onefit.restrictions.dto.RestrictionUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restriction")
@RequiredArgsConstructor
public class RestrictionController {
    private final RestrictionService restrictionService;
    @PostMapping("/create")
    public ResponseEntity<RestrictionResponseDto> create(@RequestBody @Valid RestrictionCreateDto createDto){
        RestrictionResponseDto restrictionResponseDto = restrictionService.create(createDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restrictionResponseDto);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<RestrictionResponseDto> update(
            @PathVariable UUID uuid,
            @RequestBody @Valid RestrictionUpdateDto restrictionUpdateDto
    ) {
        RestrictionResponseDto restrictionResponseDto = restrictionService.update(uuid, restrictionUpdateDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restrictionResponseDto);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        restrictionService.delete(uuid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<RestrictionResponseDto>> getAll(
            @RequestParam(required = false)
            String predicate,
            Pageable pageable) {
        Page<RestrictionResponseDto> restrictionResponseDto = restrictionService.getAll(predicate, pageable);
        return ResponseEntity.ok(restrictionResponseDto);

    }
    @GetMapping("/{uuid}")
    public ResponseEntity<RestrictionResponseDto> get(@PathVariable UUID uuid) {
        RestrictionResponseDto restrictionResponseDto = restrictionService.getById(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restrictionResponseDto);
    }
}
