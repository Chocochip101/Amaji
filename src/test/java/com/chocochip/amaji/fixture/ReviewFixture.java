package com.chocochip.amaji.fixture;

import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.review.domain.Review;

public class ReviewFixture {
    private ReviewFixture(){}

    public static Review createReviewFirst(Restaurant restaurant) {
        return Review.builder()
                .url("https://m.blog.naver.com/bryanlikes/221558048613")
                .restaurant(restaurant).build();
    }

    public static Review createReviewSecond(Restaurant restaurant) {
        return Review.builder()
                .url("https://m.blog.naver.com/choco0429/221472148603")
                .restaurant(restaurant).build();
    }

}
