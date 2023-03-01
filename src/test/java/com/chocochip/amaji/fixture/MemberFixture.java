package com.chocochip.amaji.fixture;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;

public class MemberFixture {
    private MemberFixture(){}

    public static Member createMemberChocochip() {
        return Member.builder()
                .name("chocochip")
                .email("dev.chocochip@gmail.com")
                .nickname("chocochip")
                .picture("aaa")
                .member_role(Role.USER)
                .isDeleted(false).build();
    }

    public static Member createMemberVanillachip() {
        return Member.builder()
                .name("vanillachip")
                .email("dev.vanillachip@gmail.com")
                .nickname("vanillachip")
                .picture("bbb")
                .member_role(Role.USER)
                .isDeleted(false).build();
    }
}
