package com.example.onefit.category;

import com.example.onefit.category.dto.CategoryCreateDto;
import com.example.onefit.category.dto.CategoryResponseDto;
import com.example.onefit.category.dto.CategoryUpdateDto;
import com.example.onefit.category.entity.Category;
import com.example.onefit.common.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CategoryMapperDto extends GenericMapper<Category, CategoryCreateDto, CategoryResponseDto, CategoryUpdateDto> {

    private final ModelMapper modelMapper;

    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        return modelMapper.map(categoryCreateDto, Category.class);
    }

    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void toEntity(CategoryUpdateDto categoryUpdateDto, Category category) {
        modelMapper.map(categoryUpdateDto, category);
    }

}
