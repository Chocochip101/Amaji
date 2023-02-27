package com.chocochip.amaji;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepository memberRepository;
        private final RestaurantRepository restaurantRepository;

        private final CityRepository cityRepository;
        public void dbInit1() {
            Member member1 = getBuild(1L, "권기호", "dev.chocochip@gmail.com", "chocochip", "aaa", Role.USER);
            memberRepository.save(member1);

            City city = getCity(CityEnum.OSAKA);
            cityRepository.save(city);

            Restaurant restaurant = getRestaurant(city, 1L, "서울 규카츠", "서울특별시 광진구 광나루로", 4.5, 111.11, 222.22);
            restaurantRepository.save(restaurant);
        }

        private static City getCity(CityEnum cityName) {
            City city = City.builder()
                    .name(cityName).build();
            return city;
        }

        private static Restaurant getRestaurant(City city, Long id, String name, String address, Double rating,Double longitude, Double latitude) {
            Restaurant restaurant = Restaurant.builder()
                    .id(id)
                    .name(name)
                    .address(address)
                    .rating(rating)
                    .longitude(longitude)
                    .latitude(latitude)
                    .city(city).build();
            return restaurant;
        }

        private static Member getBuild(Long id, String name, String email, String nickname, String pic, Role role) {
            return Member.builder()
                    .id(id)
                    .name(name)
                    .email(email)
                    .nickname(nickname)
                    .picture(pic)
                    .member_role(role)
                    .isDeleted(false).build();
        }
    }
}
