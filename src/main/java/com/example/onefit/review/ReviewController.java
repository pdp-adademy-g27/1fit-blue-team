package com.example.onefit.review;

import com.example.onefit.common.AppConstants;
import com.example.onefit.review.dto.ReviewCreateDto;
import com.example.onefit.review.dto.ReviewResponseDto;
import com.example.onefit.review.dto.ReviewUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ReviewController.BASE_URL)
@RequiredArgsConstructor
public class ReviewController {
    public static final String BASE_URL = "/review";
    private final ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> create(@RequestBody ReviewCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> update(@RequestBody ReviewUpdateDto updateDto, @PathVariable UUID id) {
        return ResponseEntity.ok(service.internalUpdate(id, updateDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    public ResponseEntity<Page<ReviewResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        return ResponseEntity.ok(service.getAll(predicate,pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
