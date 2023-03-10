package com.chocochip.amaji.menuResturant.domain;

import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MenuRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_restaurant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    private MenuSort menu_sort = MenuSort.ETC; //메뉴의 종류

    @Builder
    public MenuRestaurant(Long id, Restaurant restaurant, Menu menu, MenuSort menu_sort) {
        this.id = id;
        this.menu = menu;
        this.restaurant = restaurant;
        this.menu_sort = menu_sort;
    }
}
