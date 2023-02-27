package com.chocochip.amaji.review.domain.repository;

import com.chocochip.amaji.review.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findReviewByRestaurantId(Long restaurantId);
}
