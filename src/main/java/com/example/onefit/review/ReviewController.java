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
@RequestMapping(AppConstants.BASE_PATH + ReviewController.BASE_URL)
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

    //todo Find all reviews which belong to one course/gym

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
