package com.example.onefit.course.dto;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseDtoMapper extends GenericMapper<Course, CourseCreateDto, CourseResponseDto, CourseUpdateDto> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Course toEntity(CourseCreateDto courseCreateDto) {
        return modelMapper.map(courseCreateDto, Course.class);
    }

    @Override
    public CourseResponseDto toResponseDto(Course course) {
        CourseResponseDto responseDto = modelMapper.map(course, CourseResponseDto.class);
        responseDto.setTrainerId(course.getTrainer().getId());
        return responseDto;
    }

    @Override
    public void toEntity(CourseUpdateDto courseUpdateDto, Course course) {
        modelMapper.map(courseUpdateDto, course);
    }
}
