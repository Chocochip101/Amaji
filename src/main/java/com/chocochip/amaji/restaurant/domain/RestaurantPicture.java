package com.chocochip.amaji.restaurant.domain;

import com.chocochip.amaji.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RestaurantPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_picture_id")
    private Long id;

    @Column(name = "restaurant_picture_url")
    private String url;

    //=========  다대일   =========//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    public RestaurantPicture(Long id, String url, Restaurant restaurant) {
        this.id = id;
        this.url = url;
        this.restaurant = restaurant;
    }
}
