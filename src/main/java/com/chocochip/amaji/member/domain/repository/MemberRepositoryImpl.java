package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.security.custom.OAuthProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.chocochip.amaji.member.domain.QMember.member;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Optional<Member> findByEmailAndProvider(String email, OAuthProvider provider){
        return queryFactory
                .selectFrom(member)
                .where(member.email.eq(email), member.oauth.provider.eq(provider))
                .stream().findAny();
    }

    public Long updateMemberNickname(Long memberId, String nickname){
        return queryFactory
                .update(member)
                .where(member.id.eq(memberId))
                .set(member.nickname, nickname)
                .execute();
    }

    public Optional<Member> findOneMemberByMemberId(Long memberId){
        return queryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .stream().findAny();
    }
}
