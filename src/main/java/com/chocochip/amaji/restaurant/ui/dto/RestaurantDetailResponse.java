package com.chocochip.amaji.restaurant.ui.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantDetailResponse {
    private Long restaurant_id;

    private String restaurant_name;

    private String restaurant_address;

    private Double longitude;

    private Double latitude;

    private Double rating;

    private List<String> restaurant_photo_url;

    private List<String> restaurant_review_url;

    private List<String> menu_name;

    public RestaurantDetailResponse(String restaurant_name, String restaurant_address, Double longitude, Double latitude, Double rating,
                                    List<String> restaurant_photo_url, List<String> restaurant_review_url, List<String> menu_name) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rating = rating;
        this.restaurant_photo_url = restaurant_photo_url;
        this.restaurant_review_url = restaurant_review_url;
        this.menu_name = menu_name;
    }
}
