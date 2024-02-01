package com.example.onefit.review;
import com.example.onefit.common.service.GenericService;
import com.example.onefit.course.repository.CourseRepository;
import com.example.onefit.course.entity.Course;
import com.example.onefit.review.dto.ReviewCreateDto;
import com.example.onefit.review.dto.ReviewResponseDto;
import com.example.onefit.review.dto.ReviewUpdateDto;
import com.example.onefit.review.entity.Review;
import com.example.onefit.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import com.example.onefit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ReviewService extends GenericService<Review, UUID, ReviewResponseDto, ReviewCreateDto, ReviewUpdateDto> {
    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final Class<Review> entityClass = Review.class;
    private final ReviewDtoMapper mapper;

    @Override
    @Transactional
    protected ReviewResponseDto internalCreate(ReviewCreateDto createDto) {
        Review review = mapper.toEntity(createDto);
        setAuthor(createDto, review);
        setCourse(createDto, review);
        return mapper.toResponseDto(repository.save(review));
    }

    @Override
    @Transactional
    protected ReviewResponseDto internalUpdate(UUID id, ReviewUpdateDto updateDto) {
        Review review = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(updateDto, review);
        return mapper.toResponseDto(repository.save(review));
    }

    private void setCourse(ReviewCreateDto createDto, Review review) {
        Course course = courseRepository.findById(createDto.getCourseId()).orElseThrow(EntityNotFoundException::new);
        review.setCourse(course);
    }

    private void setAuthor(ReviewCreateDto createDto, Review review) {
        User user = userRepository.findById(createDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        review.setAuthor(user);
    }

}
