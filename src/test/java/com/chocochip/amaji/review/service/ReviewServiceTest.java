package com.chocochip.amaji.review.service;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import com.chocochip.amaji.review.domain.Review;
import com.chocochip.amaji.review.domain.repository.ReviewRepository;
import com.chocochip.amaji.review.ui.dto.ReviewResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurant;
import static com.chocochip.amaji.fixture.ReviewFixture.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    private Review review1;
    private Review review2;

    private Restaurant restaurant1;

    @BeforeEach
    void setUp(){
        reviewRepository.deleteAll();


        restaurant1 = createRestaurant();
        restaurantRepository.save(restaurant1);

        review1 = createReviewFirst(restaurant1);
        reviewRepository.save(review1);

        review2 = createReviewSecond(restaurant1);
        reviewRepository.save(review2);
    }

    @DisplayName("리뷰를 조회한다.")
    @Test
    void getAllReview() {
        // given & when
        List<ReviewResponse> reviewResponseList = reviewService.findReviewByRestaurantId(restaurant1.getId());

        // then
        Assertions.assertEquals(reviewResponseList.size(), 2);
        Assertions.assertEquals(reviewResponseList.get(0).getReview_url(), review1.getUrl());
        Assertions.assertEquals(reviewResponseList.get(1).getReview_url(), review2.getUrl());
    }
}