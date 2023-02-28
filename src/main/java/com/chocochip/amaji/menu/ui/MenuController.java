package com.chocochip.amaji.menu.ui;

import com.chocochip.amaji.menu.domain.repository.MenuRepository;
import com.chocochip.amaji.menu.service.MenuService;
import com.chocochip.amaji.menu.ui.dto.MenuResponse;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/restaurant/{restaurant-id}")
    public ResponseEntity<List<MenuResponse>> getAllMenuByRestaurantId(@PathVariable("restaurant-id") Long restaurantId){
        List<MenuResponse> menuResponseList = menuService.findAllMenuByRestaurant(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponseList);
    }
}
