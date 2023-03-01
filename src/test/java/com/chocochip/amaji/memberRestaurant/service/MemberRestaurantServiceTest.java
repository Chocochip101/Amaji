package com.chocochip.amaji.memberRestaurant.service;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import com.chocochip.amaji.memberRestaurant.domain.dto.MemberRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
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
import static com.chocochip.amaji.fixture.MemberFixture.createMemberChocochip;
import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurant;
import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurantWithCity;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRestaurantServiceTest {
    @Autowired MemberRestaurantService memberRestaurantService;
    @Autowired MemberRestaurantRepository memberRestaurantRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired
    CityRepository cityRepository;

    private Member member1;
    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private City city;
    private MemberRestaurant memberRestaurant1;
    private MemberRestaurant memberRestaurant2;
    @BeforeEach
    void setUp(){
        memberRestaurantRepository.deleteAll();
        memberRepository.deleteAll();
        restaurantRepository.deleteAll();
        cityRepository.deleteAll();

        member1 = createMemberChocochip();
        memberRepository.save(member1);

        city = createCityOsaka();
        cityRepository.save(city);

        restaurant1 = createRestaurant();
        restaurantRepository.save(restaurant1);

        restaurant2 = createRestaurantWithCity(city);
        restaurantRepository.save(restaurant2);

        memberRestaurant1 = MemberRestaurant.builder().member(member1).restaurant(restaurant1).build();
        memberRestaurantRepository.save(memberRestaurant1);

        memberRestaurant2 = MemberRestaurant.builder().member(member1).restaurant(restaurant2).build();
        memberRestaurantRepository.save(memberRestaurant2);
    }


    @DisplayName("유저 id로 관심있는 식당 리스트를 조회한다.")
    @Test
    void getMemberRestaurant() {
        // given & when
        List<RestaurantResponse> restaurantResponseList = memberRestaurantService.getMemberRestaurant(member1.getId());

        // then
        Assertions.assertEquals(restaurantResponseList.size(), 2);
        Assertions.assertEquals(restaurantResponseList.get(0).getRestaurant_name(), restaurant1.getName());
        Assertions.assertEquals(restaurantResponseList.get(1).getRestaurant_name(), restaurant2.getName());
        Assertions.assertEquals(restaurantResponseList.get(1).getRestaurant_name(), restaurant2.getName());
    }

    @DisplayName("유저의 관심 식당으로 등록한다.")
    @Test
    void saveMemberRestaurant() {

        // given
        Restaurant restaurantNew = Restaurant.builder().name("newRes").address("aaa").latitude(12.1).longitude(32.1).build();
        restaurantRepository.save(restaurantNew);

        // when
        memberRestaurantService.saveMemberRestaurant(member1.getId(), restaurantNew.getId());

        List<RestaurantResponse> restaurantResponseList = memberRestaurantService.getMemberRestaurant(member1.getId());

        // then
        Assertions.assertEquals(restaurantResponseList.size(), 3);
        Assertions.assertEquals(restaurantResponseList.get(2).getRestaurant_name(), restaurantNew.getName());
    }

    @DisplayName("유저의 관심 식당에서 삭제한다.")
    @Test
    void deleteMemberRestaurant(){
        // given & when
        memberRestaurantService.deleteMemberRestaurant(member1.getId(), restaurant2.getId());

        List<RestaurantResponse> restaurantResponseList = memberRestaurantService.getMemberRestaurant(member1.getId());

        // then
        Assertions.assertEquals(restaurantResponseList.size(), 1);
        Assertions.assertEquals(restaurantResponseList.get(0).getRestaurant_name(), restaurant1.getName());
    }
}