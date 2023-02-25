package com.chocochip.amaji.member.mapper;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "user_id", source = "id")
    @Mapping(target = "user_nickname", source = "nickname")
    @Mapping(target = "user_picture_url", source = "picture")
    MemberResponse memberEntityToResponse(Member member);
}
