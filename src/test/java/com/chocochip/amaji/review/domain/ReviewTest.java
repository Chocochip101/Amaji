package com.chocochip.amaji.review.domain;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    @DisplayName("리뷰를 빌더패턴을 이용해서 생성할 수 있다.")
    @Test
    public void createReview() throws Exception {
        //given
        String reviewUrl = "url";

        String name = "restaurant";
        String address = "Japan";
        Double rating = 4.7;
        Double latitude = 24.7;
        Double longitude = 4.37;

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .rating(rating)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        //when
        Review review = Review.builder()
                .url(reviewUrl)
                .restaurant(restaurant)
                .build();

        //then
        Assertions.assertEquals(review.getUrl(), reviewUrl);
        Assertions.assertEquals(review.getRestaurant(), restaurant);
    }

}