package com.chocochip.amaji.restaurant.domain.repository;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository  extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
}
