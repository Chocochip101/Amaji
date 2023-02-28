package com.chocochip.amaji.menuResturant.domain.dto;

import com.chocochip.amaji.menu.domain.Menu;

import java.util.List;

public interface MenuRestaurantRepositoryCustom {
    List<Menu> findMenuByRestaurantId(Long restaurantId);
}
