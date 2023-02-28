package com.chocochip.amaji.review.ui;

import com.chocochip.amaji.review.service.ReviewService;
import com.chocochip.amaji.review.ui.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping("/restaurant/{restaurant-id}")
    public ResponseEntity<List<ReviewResponse>> getRestaurantReviews(@PathVariable("restaurant-id") Long restaurantId){
        List<ReviewResponse> reviewResponseList = reviewService.findReviewByRestaurantId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseList);
    }
}
