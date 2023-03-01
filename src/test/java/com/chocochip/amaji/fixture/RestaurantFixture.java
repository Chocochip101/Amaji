package com.chocochip.amaji.fixture;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.restaurant.domain.Restaurant;

public class RestaurantFixture {
    private RestaurantFixture(){}


    public static Restaurant createRestaurantWithCity(City city) {
        String name = "restaurant1";
        String address = "Japan1";
        Double rating = 4.7;
        Double latitude = 24.7;
        Double longitude = 4.37;
        return Restaurant.builder()
                .name(name)
                .address(address)
                .rating(rating)
                .latitude(latitude)
                .longitude(longitude)
                .city(city).build();
    }

    public static Restaurant createRestaurant() {
        String name = "restaurant2";
        String address = "Japan2";
        Double rating = 4.3;
        Double latitude = 24.7;
        Double longitude = 4.37;
        return Restaurant.builder()
                .name(name)
                .address(address)
                .rating(rating)
                .latitude(latitude)
                .longitude(longitude).build();
    }
}
