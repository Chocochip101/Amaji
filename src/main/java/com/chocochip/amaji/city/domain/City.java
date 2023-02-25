package com.chocochip.amaji.city.domain;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city_name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Restaurant> restaurantList = new ArrayList<>();

    @Builder
    public City(Long id, String name, List<Restaurant> restaurantList) {
        this.id = id;
        this.name = name;
        this.restaurantList = restaurantList;
    }
}
