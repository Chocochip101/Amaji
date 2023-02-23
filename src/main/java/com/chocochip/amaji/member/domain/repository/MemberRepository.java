package com.chocochip.amaji.member.domain.repository;

import com.chocochip.amaji.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
