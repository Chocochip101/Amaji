package com.chocochip.amaji.member.domain.dto;

import com.chocochip.amaji.global.util.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private Long member_id;
    private String member_name;
    private String member_nickname;
    private String member_email;
    private String member_picture;
    private String provider;
    private Role member_role;

    @QueryProjection
    @Builder
    public MemberDto(Long member_id, String member_name, String member_nickname, String member_email, String member_picture, String provider, Role member_role) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_nickname = member_nickname;
        this.member_email = member_email;
        this.member_picture = member_picture;
        this.provider = provider;
        this.member_role = member_role;
    }


}
