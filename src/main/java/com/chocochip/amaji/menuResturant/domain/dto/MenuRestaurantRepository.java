package com.chocochip.amaji.menuResturant.domain.dto;

import com.chocochip.amaji.menuResturant.domain.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant, Long>, MenuRestaurantRepositoryCustom {
}
