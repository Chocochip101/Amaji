package com.chocochip.amaji.member.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberResponse {
    private Long user_id;

    private String user_nickname;

    private String user_picture_url;

}
