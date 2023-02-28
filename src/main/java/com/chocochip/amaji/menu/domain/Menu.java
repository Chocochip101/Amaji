package com.chocochip.amaji.menu.domain;

import com.chocochip.amaji.menuResturant.domain.MenuRestaurant;
import com.chocochip.amaji.restaurant.domain.RestaurantPicture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    private Integer price;

    //=========  일대다   =========//
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuRestaurant> menuRestaurantList = new ArrayList<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuPicture> menuPictureList = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, Integer price, List<MenuRestaurant> menuRestaurantList, List<MenuPicture> menuPictureList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuRestaurantList = menuRestaurantList;
        this.menuPictureList = menuPictureList;
    }
}
