package com.example.onefit.review;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.review.dto.ReviewCreateDto;
import com.example.onefit.review.dto.ReviewResponseDto;
import com.example.onefit.review.dto.ReviewUpdateDto;
import com.example.onefit.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ReviewDtoMapper extends GenericMapper<Review, ReviewCreateDto, ReviewResponseDto, ReviewUpdateDto> {
    private final ModelMapper modelMapper;

    @Override
    public Review toEntity(ReviewCreateDto reviewCreateDto) {
        Review review = modelMapper.map(reviewCreateDto, Review.class);
        review.setId(UUID.randomUUID());
        review.setPublishedAt(LocalDateTime.now());
        return review;
    }

    @Override
    public ReviewResponseDto toResponseDto(Review review) {
        ReviewResponseDto responseDto = modelMapper.map(review, ReviewResponseDto.class);
        responseDto.setAuthor(review.getAuthor().getName());
        return responseDto;
    }

    @Override
    public void toEntity(ReviewUpdateDto reviewUpdateDto, Review review) {
        modelMapper.map(reviewUpdateDto, review);
        review.setChanged(true);
    }
}
