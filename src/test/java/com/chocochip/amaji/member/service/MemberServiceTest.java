package com.chocochip.amaji.member.service;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.member.ui.dto.MemberNicknameRequest;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.chocochip.amaji.fixture.MemberFixture.createMemberChocochip;
import static com.chocochip.amaji.fixture.MemberFixture.createMemberVanillachip;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    private Member member1;
    private Member member2;

    @BeforeEach
    void setUp(){
        memberRepository.deleteAll();

        member1 = createMemberChocochip();
        memberRepository.save(member1);

        member2 = createMemberVanillachip();
        memberRepository.save(member2);
    }


    @DisplayName("유저를 조회한다.")
    @Test
    void getAllMemebers() {
        // given & when
        MemberResponse member = memberService.findOneMember(member1.getId());

        // then
        Assertions.assertEquals(member.getUser_id(), member1.getId());
        Assertions.assertEquals(member.getUser_nickname(), member1.getNickname());
        Assertions.assertEquals(member.getUser_picture_url(), member1.getPicture());
    }

    @DisplayName("유저의 닉네임을 변경한다")
    @Test
    void changeMemebersNickname() {
        // given
        String changeName = "chocochocochip";
        MemberNicknameRequest memberNicknameRequest = new MemberNicknameRequest(changeName);

        // when
        memberService.changeMemberNickname(member2.getId(), memberNicknameRequest);
        Optional<Member> member = memberRepository.findById(member2.getId());

        // then
        Assertions.assertEquals(member.get().getNickname(), changeName);
    }
}