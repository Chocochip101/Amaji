package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.security.custom.OAuthProvider;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByEmailAndProvider(String email, OAuthProvider provider);

    Long updateMemberNickname(Long memberId, String nickname);

    Optional<Member> findOneMemberByMemberId(Long memberId);
}
