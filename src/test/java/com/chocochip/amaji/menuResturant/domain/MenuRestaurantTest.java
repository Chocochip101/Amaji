package com.chocochip.amaji.menuResturant.domain;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuRestaurantTest {
    @DisplayName("식당의 메뉴를 빌더 패턴을 활용하여 생성할 수 있다")
    @Test
    public void createMenuRestaurant() throws Exception {
        //given
        Long id = 1L;
        Integer price = 10000;
        String menu_name = "규카츠";

        Menu menu = Menu.builder()
                .name(menu_name)
                .price(price)
                .build();

        String name = "restaurant";
        String address = "Japan";
        Double rating = 4.7;
        Double latitude = 24.7;
        Double longitude = 4.37;

        CityEnum cityName = CityEnum.OSAKA;

        City city = City.builder()
                .name(cityName).build();

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .rating(rating)
                .latitude(latitude)
                .longitude(longitude)
                .city(city).build();

        MenuSort menuSort = MenuSort.RAMEN;
        //when
        MenuRestaurant menuRestaurant = MenuRestaurant.builder()
                .restaurant(restaurant)
                        .menu(menu)
                .menu_sort(menuSort)
                                .build();

        //then
        Assertions.assertEquals(menuRestaurant.getMenu(), menu);
        Assertions.assertEquals(menuRestaurant.getRestaurant(), restaurant);
        Assertions.assertEquals(menuRestaurant.getMenu_sort(), menuSort);

    }

}