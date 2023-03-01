package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void setUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("이미 DB에 저장되어 있는 ID를 가진 사용자를 저장하면, 해당 ID의 사용자는 후에 작성된 사용자 정보로 업데이트 된다.")
    @Test
    void saveSameId() {
        // given
        Member member1 = Member.builder()
                .name("user1").email("dev.chocochip@gmail.com").nickname("chocochip").member_role(Role.USER).picture("my_pic_url").build();
        memberRepository.save(member1);

        // when
        Member member2 = Member.builder().id(member1.getId())
                .name("user2").email("dev.chocochip2@gmail.com").nickname("chocochip2").member_role(Role.USER).picture("my_pic_url2").build();
        memberRepository.save(member2);

        // then
        List<Member> savedMembers = memberRepository.findAll();
        Assertions.assertEquals(savedMembers.size(), 1);

        Member foundMember = memberRepository.findById(member1.getId()).get();
        Assertions.assertEquals(foundMember.getName(), "user2");
    }


}