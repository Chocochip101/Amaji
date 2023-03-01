package com.chocochip.amaji.restaurant.service;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.memberRestaurant.domain.dto.MemberRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantDetailResponse;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.chocochip.amaji.fixture.CityFixture.createCityOsaka;
import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurant;
import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurantWithCity;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestaurantServiceTest {
    @Autowired RestaurantService restaurantService;
    @Autowired MemberRestaurantRepository memberRestaurantRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired CityRepository cityRepository;

    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private City city;
    @BeforeEach
    void setUp(){
        restaurantRepository.deleteAll();

        city = createCityOsaka();
        cityRepository.save(city);

        restaurant1 = createRestaurant();
        restaurantRepository.save(restaurant1);

        restaurant2 = createRestaurantWithCity(city);
        restaurantRepository.save(restaurant2);
    }

    @DisplayName("모든 식당을 조회한다.")
    @Test
    void findRestaurantTest() {
        // given & when
        List<RestaurantResponse> restaurantResponseList = restaurantService.findRestaurant(null);

        // then
        Assertions.assertEquals(restaurantResponseList.size(), 2);
        Assertions.assertEquals(restaurantResponseList.get(0).getRestaurant_name(), restaurant1.getName());
        Assertions.assertEquals(restaurantResponseList.get(1).getRestaurant_name(), restaurant2.getName());
    }

    @DisplayName("도시 별로 식당을 조회한다.")
    @Test
    void findRestaurantWithCityTest() {
        // given & when
        List<RestaurantResponse> restaurantResponseList = restaurantService.findRestaurant("osaka");

        // then
        Assertions.assertEquals(restaurantResponseList.size(), 1);
        Assertions.assertEquals(restaurantResponseList.get(0).getRestaurant_name(), restaurant2.getName());
    }

    @DisplayName("식당의 detail을 조회한다.")
    @Test
    void findRestaurantDetailTest() {
        // given & when
        RestaurantDetailResponse restaurantDetail = restaurantService.findRestaurantDetail(restaurant2.getId());

        // then
        Assertions.assertEquals(restaurantDetail.getRestaurant_name(), restaurant2.getName());
        Assertions.assertEquals(restaurantDetail.getRestaurant_address(), restaurant2.getAddress());
        Assertions.assertEquals(restaurantDetail.getLatitude(), restaurant2.getLatitude());
        Assertions.assertEquals(restaurantDetail.getLongitude(), restaurant2.getLongitude());
    }

}