package com.chocochip.amaji.review.mapper;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.mapper.RestaurantMapper;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import com.chocochip.amaji.review.domain.Review;
import com.chocochip.amaji.review.ui.dto.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "review_url", source = "url")
    ReviewResponse reviewEntityToResponse(Review review);
}
