package com.chocochip.amaji.menu.service;

import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.menu.domain.repository.MenuRepository;
import com.chocochip.amaji.menu.ui.dto.MenuResponse;
import com.chocochip.amaji.menuResturant.domain.MenuRestaurant;
import com.chocochip.amaji.menuResturant.domain.dto.MenuRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.chocochip.amaji.fixture.MenuFixture.createRamen;
import static com.chocochip.amaji.fixture.MenuFixture.createSushi;
import static com.chocochip.amaji.fixture.RestaurantFixture.createRestaurant;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MenuServiceTest {
    @Autowired MenuService menuService;

    @Autowired  MenuRepository menuRepository;
    @Autowired RestaurantRepository restaurantRepository;
    @Autowired MenuRestaurantRepository menuRestaurantRepository;

    private Menu menu1;

    private Menu menu2;
    private Restaurant restaurant;
    private MenuRestaurant menuRestaurant1;
    private MenuRestaurant menuRestaurant2;
    @BeforeEach
    void setUp(){
        menuRepository.deleteAll();
        menuRestaurantRepository.deleteAll();
        restaurantRepository.deleteAll();

        menu1 = createSushi();
        menuRepository.save(menu1);

        menu2 = createRamen();
        menuRepository.save(menu2);

        restaurant = createRestaurant();
        restaurantRepository.save(restaurant);

        menuRestaurant1 = MenuRestaurant.builder().menu(menu1).restaurant(restaurant).build();
        menuRestaurantRepository.save(menuRestaurant1);
        menuRestaurant2 = MenuRestaurant.builder().menu(menu2).restaurant(restaurant).build();
        menuRestaurantRepository.save(menuRestaurant2);
    }

    @DisplayName("메뉴를 조회한다.")
    @Test
    void getAllMenu() {
        // given & when
        List<MenuResponse> menuResponseList = menuService.findAllMenuByRestaurant(restaurant.getId());

        // then
        Assertions.assertEquals(menuResponseList.size(), 2);
        Assertions.assertEquals(menuResponseList.get(0).getMenu_name(), menu1.getName());
        Assertions.assertEquals(menuResponseList.get(1).getMenu_name(), menu2.getName());
    }

}