package com.chocochip.amaji.restaurant.domain;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.global.util.CityEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantTest {

    @DisplayName("식당을 빌더 패턴을 활용하여 생성할 수 있다")
    @Test
    public void createRestaurant() throws Exception {
        //given
        String name = "restaurant";
        String address = "Japan";
        Double rating = 4.7;
        Double latitude = 24.7;
        Double longitude = 4.37;

        CityEnum cityName = CityEnum.OSAKA;

        City city = City.builder()
                .name(cityName).build();

        //when
        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .rating(rating)
                .latitude(latitude)
                .longitude(longitude)
                .city(city).build();

        //then
        Assertions.assertEquals(restaurant.getName(), name);
        Assertions.assertEquals(restaurant.getAddress(), address);
        Assertions.assertEquals(restaurant.getRating(), rating);
        Assertions.assertEquals(restaurant.getLongitude(), longitude);
        Assertions.assertEquals(restaurant.getLatitude(), latitude);
        Assertions.assertEquals(restaurant.getCity(), city);
    }

}