package com.chocochip.amaji.member.domain;

import com.chocochip.amaji.global.util.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @DisplayName("사용자를 빌더 패턴을 활용하여 생성할 수 있다")
    @Test
    public void createUser() throws Exception {
        //given
        Long id = 1L;
        String name = "chocochip";
        String nickname = "choco";
        String email = "devchocochip@gmail.com";
        String picture = "picture";
        Role role = Role.USER;

        //when
        Member member = Member.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .member_role(role)
                .picture(picture).build();

        //then
        Assertions.assertEquals(member.getName(), name);
        Assertions.assertEquals(member.getNickname(), nickname);
        Assertions.assertEquals(member.getEmail(), email);
        Assertions.assertEquals(member.getPicture(), picture);
        Assertions.assertEquals(member.getMember_role(), role);

    }
}