package com.example.onefit.category;

import com.example.onefit.category.dto.CategoryCreateDto;
import com.example.onefit.category.dto.CategoryResponseDto;
import com.example.onefit.category.dto.CategoryUpdateDto;
import com.example.onefit.common.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + CategoryController.BASE_URL)
@RequiredArgsConstructor
public class CategoryController {
    public static final String BASE_URL = "/category";
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody @Valid CategoryCreateDto createDto){
        CategoryResponseDto categoryResponseDto = categoryService.create(createDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponseDto);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable UUID uuid,
            @RequestBody @Valid CategoryUpdateDto categoryUpdateDto
    ) {
        CategoryResponseDto categoryResponseDto = categoryService.update(uuid, categoryUpdateDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryResponseDto);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        categoryService.delete(uuid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryResponseDto> get(@PathVariable UUID uuid) {
        CategoryResponseDto categoryResponseDto = categoryService.getById(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>> getAll(
            @RequestParam(required = false)
            String predicate,
            Pageable pageable) {
        Page<CategoryResponseDto> categoryResponseDto = categoryService.getAll(predicate, pageable);
        return ResponseEntity.ok(categoryResponseDto);

    }



}
