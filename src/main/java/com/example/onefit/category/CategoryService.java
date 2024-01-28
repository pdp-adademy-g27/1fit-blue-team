package com.example.onefit.category;

import com.example.onefit.category.dto.CategoryCreateDto;
import com.example.onefit.category.dto.CategoryResponseDto;
import com.example.onefit.category.dto.CategoryUpdateDto;
import com.example.onefit.category.entity.Category;
import com.example.onefit.common.service.GenericService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class CategoryService extends GenericService <Category, UUID, CategoryResponseDto, CategoryCreateDto,  CategoryUpdateDto>{

    private final CategoryRepository repository;
    private final Class<Category> entityClass = Category.class;
    private final CategoryMapperDto mapper;

    @Override
    protected CategoryResponseDto internalCreate(CategoryCreateDto categoryCreateDto) {
        Category entity = mapper.toEntity(categoryCreateDto);
        entity.setId(UUID.randomUUID());

        Category saved = repository.save(entity);

        return mapper.toResponseDto(saved);
    }

    @Override
    protected CategoryResponseDto internalUpdate(UUID uuid, CategoryUpdateDto categoryUpdateDto) {

        Category existingCategory = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + uuid));

        mapper.toEntity(categoryUpdateDto, existingCategory);

        Category updatedCategory = repository.save(existingCategory);

        return mapper.toResponseDto(updatedCategory);
    }

    protected void internalDelete(UUID uuid) {
        Category existingCategory = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + uuid));

        repository.delete(existingCategory);
    }

    public CategoryResponseDto getById(UUID uuid) {
        Category existingCategory = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + uuid));

        return mapper.toResponseDto(existingCategory);
    }

    protected List<CategoryResponseDto> getAll() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }


}
