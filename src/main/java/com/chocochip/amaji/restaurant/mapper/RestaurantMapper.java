package com.chocochip.amaji.restaurant.mapper;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.mapper.MemberMapper;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantDetailResponse;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "restaurant_id", source = "id")
    @Mapping(target = "restaurant_name", source = "name")
    RestaurantResponse restaurantEntityToResponse(Restaurant restaurant);

}
