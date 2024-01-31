package com.example.onefit.category;

import com.example.onefit.category.dto.CategoryCreateDto;
import com.example.onefit.category.dto.CategoryResponseDto;
import com.example.onefit.category.dto.CategoryUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
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
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable UUID uuid) {
        CategoryResponseDto categoryResponseDto = categoryService.get(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> categoryList = categoryService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryList);
    }

}
