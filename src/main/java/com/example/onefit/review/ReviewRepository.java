package com.example.onefit.review;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.review.entity.Review;

import java.util.UUID;

public interface ReviewRepository extends GenericRepository<Review, UUID> {
}
