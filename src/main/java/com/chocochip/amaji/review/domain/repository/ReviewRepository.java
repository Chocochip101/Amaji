package com.chocochip.amaji.review.domain.repository;

import com.chocochip.amaji.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
