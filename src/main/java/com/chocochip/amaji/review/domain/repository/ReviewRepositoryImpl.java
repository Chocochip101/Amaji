package com.chocochip.amaji.review.domain.repository;

import com.chocochip.amaji.review.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.chocochip.amaji.review.domain.QReview.review;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    public List<Review> findReviewByRestaurantId(Long restaurantId){
        return queryFactory
                .selectFrom(review)
                .where(review.restaurant.id.eq(restaurantId))
                .stream().toList();
    }
}
