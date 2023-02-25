package com.chocochip.amaji.member.service;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.member.mapper.MemberMapper;
import com.chocochip.amaji.member.ui.dto.MemberNicknameRequest;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void changeMemberNickname(Long memberId, MemberNicknameRequest memberNicknameRequest){
        memberRepository.updateMemberNickname(memberId, memberNicknameRequest.getNickname());
    }

    public MemberResponse findOneMember(Long memberId){
        Optional<Member> optionalMember = memberRepository.findOneMemberByMemberId(memberId);
        return MemberMapper.INSTANCE.memberEntityToResponse(optionalMember.get());
    }

}
