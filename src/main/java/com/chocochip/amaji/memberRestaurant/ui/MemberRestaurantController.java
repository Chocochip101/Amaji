package com.chocochip.amaji.memberRestaurant.ui;

import com.chocochip.amaji.memberRestaurant.service.MemberRestaurantService;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/interest")
public class MemberRestaurantController {

    private final MemberRestaurantService memberRestaurantService;

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<RestaurantResponse>> getMemberInterestRestaurant(
            @PathVariable("user-id") Long memberId){
        List<RestaurantResponse> result = memberRestaurantService.getMemberRestaurant(memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/users/{user-id}/restaurant/{restaurant-id}")
    public ResponseEntity<Void> saveMemberInterestRestaurant(
            @PathVariable("user-id") Long memberId,
            @PathVariable("restaurant-id") Long restaurantId){
        memberRestaurantService.saveMemberRestaurant(memberId, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/users/{user-id}/restaurant/{restaurant-id}")
    public ResponseEntity<Void> deleteMemberInterestRestaurant(
            @PathVariable("user-id") Long memberId,
            @PathVariable("restaurant-id") Long restaurantId){
        memberRestaurantService.deleteMemberRestaurant(memberId, restaurantId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
