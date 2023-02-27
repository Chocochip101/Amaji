package com.chocochip.amaji.memberRestaurant.service;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import com.chocochip.amaji.memberRestaurant.domain.dto.MemberRestaurantRepository;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import com.chocochip.amaji.restaurant.mapper.RestaurantMapper;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberRestaurantService {

    private final MemberRepository memberRepository;

    private final RestaurantRepository restaurantRepository;

    private final MemberRestaurantRepository memberRestaurantRepository;

    public List<RestaurantResponse> getMemberRestaurant(Long memberId) {
        List<Restaurant> restaurantList = restaurantRepository.findRestaurantListByMemberId(memberId);
        return restaurantList.stream().map(RestaurantMapper.INSTANCE::restaurantEntityToResponse).collect(Collectors.toList());
    }

    @Transactional
    public void saveMemberRestaurant(Long memberId, Long restaurantId) {
        Optional<Member> optionalMember = memberRepository.findOneMemberByMemberId(memberId);

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        MemberRestaurant memberRestaurant = MemberRestaurant.builder()
                .member(optionalMember.get())
                .restaurant(optionalRestaurant.get())
                .build();

        memberRestaurantRepository.save(memberRestaurant);
    }

    @Transactional
    public void deleteMemberRestaurant(Long memberId, Long restaurantId) {
        memberRestaurantRepository.deleteMemberRestaurantByMemberIdAndRestaurantId(memberId, restaurantId);
    }
}
