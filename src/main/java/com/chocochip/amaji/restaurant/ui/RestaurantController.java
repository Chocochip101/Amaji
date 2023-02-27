package com.chocochip.amaji.restaurant.ui;

import com.chocochip.amaji.city.ui.dto.CityResponse;
import com.chocochip.amaji.restaurant.service.RestaurantService;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantDetailResponse;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getRestaurants(@RequestParam(value = "city", required = false) String cityName){
        List<RestaurantResponse> restaurantResponseList = restaurantService.findRestaurant(cityName);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseList);
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity<RestaurantDetailResponse> getRestaurantDetail(@PathVariable("restaurant-id") Long restaurantId){
        RestaurantDetailResponse restaurantDetailResponse = restaurantService.findRestaurantDetail(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantDetailResponse);
    }

}
