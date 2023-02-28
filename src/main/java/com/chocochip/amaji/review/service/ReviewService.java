package com.chocochip.amaji.review.service;

import com.chocochip.amaji.review.domain.Review;
import com.chocochip.amaji.review.domain.repository.ReviewRepository;
import com.chocochip.amaji.review.mapper.ReviewMapper;
import com.chocochip.amaji.review.ui.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> findReviewByRestaurantId(Long restaurantId) {
        List<Review> reviewList = reviewRepository.findReviewByRestaurantId(restaurantId);
        return reviewList.stream().map(ReviewMapper.INSTANCE::reviewEntityToResponse).collect(Collectors.toList());
    }
}
