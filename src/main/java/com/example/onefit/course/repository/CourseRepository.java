package com.example.onefit.course.repository;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.course.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CourseRepository extends GenericRepository<Course, UUID> {
}
