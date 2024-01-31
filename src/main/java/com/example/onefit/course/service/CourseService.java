package com.example.onefit.course.service;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.common.service.GenericService;
import com.example.onefit.course.dto.CourseCreateDto;
import com.example.onefit.course.dto.CourseDtoMapper;
import com.example.onefit.course.dto.CourseResponseDto;
import com.example.onefit.course.dto.CourseUpdateDto;
import com.example.onefit.course.entity.Course;
import com.example.onefit.course.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@Getter
@RequiredArgsConstructor
public class CourseService extends GenericService<Course, UUID, CourseResponseDto, CourseCreateDto, CourseUpdateDto> {
    private final CourseRepository courseRepository;
    private final CourseDtoMapper courseDtoMapper;
    private final Class<Course> entityClass = Course.class;

    @Override
    protected GenericRepository<Course, UUID> getRepository() {
        return courseRepository;
    }


    @Override
    protected GenericMapper<Course, CourseCreateDto, CourseResponseDto, CourseUpdateDto> getMapper() {
        return courseDtoMapper;
    }

    @Override
    protected CourseResponseDto internalCreate(CourseCreateDto courseCreateDto) {
        Course entity = courseDtoMapper.toEntity(courseCreateDto);
        entity.setId(UUID.randomUUID());
        Course saved = courseRepository.save(entity);
        return courseDtoMapper.toResponseDto(saved);
    }

    @Override
    protected CourseResponseDto internalUpdate(UUID uuid, CourseUpdateDto courseUpdateDto) {
       return  null;
    }

    public CourseResponseDto getById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Course> byId = courseRepository.findById(uuid);
        byId.orElseThrow(()-> new EntityNotFoundException("Course by id:%s not found".formatted(uuid)));
        Course course = byId.get();
        return courseDtoMapper.toResponseDto(course);
    }
}
