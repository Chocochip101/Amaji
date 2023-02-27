package com.chocochip.amaji.restaurant.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantResponse {
    private Long restaurant_id;

    private String restaurant_name;

    private Double longitude;

    private Double latitude;
}
