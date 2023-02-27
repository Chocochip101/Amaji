package com.chocochip.amaji.restaurant.domain.repository;

import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.restaurant.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> findRestaurantListByMemberId(Long memberId);

    List<Restaurant> findRestaurantByCityName(CityEnum cityName);
}
