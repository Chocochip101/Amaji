package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.oauth.domain.AuthProvider;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByEmailAndProvider(String email, AuthProvider provider);

    Long updateMemberNickname(Long memberId, String nickname);

    Optional<Member> findOneMemberByMemberId(Long memberId);

    String getRefreshTokenById(Long id);

    void updateRefreshToken(Long id, String token);
}
