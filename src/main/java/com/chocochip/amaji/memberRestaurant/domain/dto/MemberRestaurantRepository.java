package com.chocochip.amaji.memberRestaurant.domain.dto;

import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRestaurantRepository extends JpaRepository<MemberRestaurant, Long>, MemberRestaurantRepositoryCustom {
    Long deleteMemberRestaurantByMemberIdAndRestaurantId(Long memberId, Long restaurantId);
}
