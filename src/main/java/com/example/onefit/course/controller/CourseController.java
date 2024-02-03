package com.example.onefit.course.controller;

import com.example.onefit.common.AppConstants;
import com.example.onefit.course.dto.CourseCreateDto;
import com.example.onefit.course.dto.CourseResponseDto;
import com.example.onefit.course.dto.CourseUpdateDto;
import com.example.onefit.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping(AppConstants.BASE_PATH + CourseController.BASE_URL)
@RequiredArgsConstructor
public class CourseController {
    public static final String BASE_URL = "/course";
    private final CourseService courseService;

    @PostMapping("/course/create")
    public ResponseEntity<CourseResponseDto> courseCreate(
            @RequestBody CourseCreateDto courseCreateDto) {
        CourseResponseDto courseResponseDto = courseService.create(courseCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseResponseDto);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable String id) {
        CourseResponseDto courseResponseDto = courseService.getById(id);
        if (courseResponseDto != null) {
            return ResponseEntity.ok(courseResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable String id,
            @RequestBody CourseUpdateDto courseUpdateDto) {
        CourseResponseDto courseResponseDto = courseService.update(UUID.fromString(id), courseUpdateDto);
        if (courseResponseDto != null) {
            return ResponseEntity.ok(courseResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }


}
