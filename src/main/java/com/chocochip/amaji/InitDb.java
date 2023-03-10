package com.chocochip.amaji;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import com.chocochip.amaji.memberRestaurant.domain.dto.MemberRestaurantRepository;
import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.menu.domain.repository.MenuRepository;
import com.chocochip.amaji.menuResturant.domain.MenuRestaurant;
import com.chocochip.amaji.menuResturant.domain.MenuSort;
import com.chocochip.amaji.menuResturant.domain.dto.MenuRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import com.chocochip.amaji.review.domain.Review;
import com.chocochip.amaji.review.domain.repository.ReviewRepository;
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
        private final ReviewRepository reviewRepository;
        private final CityRepository cityRepository;
        private final MenuRepository menuRepository;
        private final MenuRestaurantRepository menuRestaurantRepository;
        private final MemberRestaurantRepository memberRestaurantRepository;
        public void dbInit1() {
            Member member1 = getMember(1L, "?????????", "dev.chocochip@gmail.com", "chocochip", "aaa", Role.USER);
            memberRepository.save(member1);

            City cityOsaka = getCity(CityEnum.OSAKA);
            cityRepository.save(cityOsaka);

            City cityTokyo = getCity(CityEnum.TOKYO);
            cityRepository.save(cityTokyo);

            Restaurant restaurant1 = getRestaurant(cityOsaka, 1L, "????????? ??????????????? ??????", "1 Chome-4-16 Dotonbori, Chuo Ward, Osaka, 542-0071 ??????", 4.5, 135.50326712710788, 34.66865115940998 );
            restaurantRepository.save(restaurant1);

            Restaurant restaurant2 = getRestaurant(cityTokyo, 2L, "???????????? ?????? ?????????", "?????? ???150-0043 Tokyo, Shibuya City, Dogenzaka, 2 Chome???16???19 ???????????? 2F", 4.0, 139.69753829454956,35.66100801163414);
            restaurantRepository.save(restaurant2);

            MemberRestaurant memberRestaurant1 = getMemberRestaurant(member1, restaurant1);
            memberRestaurantRepository.save(memberRestaurant1);

            Review review1 = getReview(restaurant1, "https://m.blog.naver.com/bryanlikes/221558048613");
            reviewRepository.save(review1);

            Review review2 = getReview(restaurant1, "https://m.blog.naver.com/choco0429/221472148603");
            reviewRepository.save(review2);

            Review review3 = getReview(restaurant1, "https://lightcircle.tistory.com/41");
            reviewRepository.save(review3);

            Menu menu1 = getMenu("?????? ??????", 10000);
            menuRepository.save(menu1);

            MenuRestaurant menuRestaurant1 = getMenuRestaurant(menu1, restaurant1, MenuSort.RAMEN);
            menuRestaurantRepository.save(menuRestaurant1);

        }
        private static MemberRestaurant getMemberRestaurant(Member member, Restaurant restaurant){
            return MemberRestaurant.builder().restaurant(restaurant).member(member).build();
        }
        private static MenuRestaurant getMenuRestaurant(Menu menu, Restaurant restaurant, MenuSort menuSort){
            return MenuRestaurant.builder().menu(menu).restaurant(restaurant).menu_sort(menuSort).build();
        }
        private static Menu getMenu(String name, Integer price) {
            return  Menu.builder().name(name).price(price).build();
        }
        private static Review getReview(Restaurant restaurant, String url) {
            return  Review.builder().restaurant(restaurant).url(url).build();
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

        private static Member getMember(Long id, String name, String email, String nickname, String pic, Role role) {
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
