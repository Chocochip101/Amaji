package com.chocochip.amaji.restaurant.domain.repository;

import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.chocochip.amaji.restaurant.domain.QRestaurant.restaurant;
import static com.chocochip.amaji.memberRestaurant.domain.QMemberRestaurant.memberRestaurant;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public RestaurantRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Restaurant> findRestaurantListByMemberId(Long memberId){
        return queryFactory
                .selectFrom(restaurant)
                .join(memberRestaurant)
                .on(memberRestaurant.restaurant.id.eq(restaurant.id))
                .where(memberRestaurant.member.id.eq(memberId))
                .stream().toList();
    }

    public List<Restaurant> findRestaurantByCityName(CityEnum cityName) {
        return queryFactory
                .selectFrom(restaurant)
                .where(restaurant.city.name.eq(cityName))
                .stream().toList();
    }

}
