package com.chocochip.amaji.restaurant.domain;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.menuresturant.domain.MenuRestaurant;
import com.chocochip.amaji.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "city_name")
    private String name;

    private Double longitude;

    private Double latitude;

    @Column(name = "restaurant_name")
    private String address;

    @Column(name = "restaurant_rating")
    private Double rating;

    //=========  다대일   =========//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    //=========  일대다   =========//
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantPicture> restaurantPictures = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuRestaurant> menuRestaurantList = new ArrayList<>();


    @Builder
    public Restaurant(Long id, String name, Double longitude, Double latitude, String address, Double rating, Member member, City city, List<RestaurantPicture> restaurantPictures, List<Review> reviewList) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.rating = rating;
        this.member = member;
        this.city = city;
        this.restaurantPictures = restaurantPictures;
        this.reviewList = reviewList;
    }
}
