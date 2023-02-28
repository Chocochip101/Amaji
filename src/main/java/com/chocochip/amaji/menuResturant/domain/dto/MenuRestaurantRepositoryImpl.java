package com.chocochip.amaji.menuResturant.domain.dto;

import com.chocochip.amaji.menu.domain.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.chocochip.amaji.menu.domain.QMenu.menu;
import static com.chocochip.amaji.menuResturant.domain.QMenuRestaurant.menuRestaurant;

@Repository
public class MenuRestaurantRepositoryImpl {
    private final JPAQueryFactory queryFactory;

    public MenuRestaurantRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Menu> findMenuByRestaurantId(Long restaurantId){
        return queryFactory
                .selectFrom(menu)
                .join(menuRestaurant)
                .on(menuRestaurant.menu.id.eq(menu.id))
                .where(menuRestaurant.restaurant.id.eq(restaurantId))
                .stream().toList();
    }

}
