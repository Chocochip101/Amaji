package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.oauth.domain.AuthProvider;
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

    public Optional<Member> findByEmailAndProvider(String email, AuthProvider provider){
        return queryFactory
                .selectFrom(member)
                .where(member.email.eq(email), member.authProvider.eq(provider))
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

    public String getRefreshTokenById(Long id){
        return queryFactory
                .select(member.refreshToken)
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
    }

    public void updateRefreshToken(Long id, String token){
        queryFactory
                .update(member)
                .where(member.id.eq(id))
                .set(member.refreshToken, token)
                .execute();
    }
}
