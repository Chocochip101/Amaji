package com.chocochip.amaji.menu.service;

import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.menu.domain.MenuPicture;
import com.chocochip.amaji.menu.domain.repository.MenuRepository;
import com.chocochip.amaji.menu.ui.dto.MenuResponse;
import com.chocochip.amaji.menuResturant.domain.dto.MenuRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.mapper.RestaurantMapper;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MenuService {
    private final MenuRestaurantRepository menuRestaurantRepository;

    public List<MenuResponse> findAllMenuByRestaurant(Long restaurantId){
        List<Menu> menuList = menuRestaurantRepository.findMenuByRestaurantId(restaurantId);
        return menuList.stream().map(menu -> new MenuResponse(menu.getName(), menu.getPrice(), menu.getMenuPictureList().stream().map(MenuPicture::getUrl).collect(Collectors.toList()))).collect(Collectors.toList());
    }
}
