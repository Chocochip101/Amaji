package com.chocochip.amaji.member.ui;

import com.chocochip.amaji.city.service.CityService;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import com.chocochip.amaji.member.service.MemberService;
import com.chocochip.amaji.member.ui.dto.MemberNicknameRequest;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    private static final MemberResponse MEMBER_RESPONSE_1 =
            new MemberResponse(1L, "닉네임1", "사진1");
    @DisplayName("유저 닉네임 변경에 성공한다.")
    @Test
    void changeNicknameTest() throws Exception {
        // given & when
        MemberNicknameRequest memberNicknameRequest = new MemberNicknameRequest("changedName");
        String json = objectMapper.writeValueAsString(memberNicknameRequest);

        // then
        mockMvc.perform(patch("/users/{user-id}/nickname", MEMBER_RESPONSE_1.getUser_id())
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted())
                .andDo(print());
    }

    @DisplayName("유저 조회에 성공한다.")
    @Test
    void getOneMemberTest() throws Exception {
        // given & when
        given(memberService.findOneMember(MEMBER_RESPONSE_1.getUser_id())).willReturn(MEMBER_RESPONSE_1);

        // then
        mockMvc.perform(get("/users/{user-id}", MEMBER_RESPONSE_1.getUser_id())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(MEMBER_RESPONSE_1.getUser_id()))
                .andExpect(jsonPath("$.user_nickname").value(MEMBER_RESPONSE_1.getUser_nickname()))
                .andExpect(
                        jsonPath("$.user_picture_url").value(MEMBER_RESPONSE_1.getUser_picture_url()))
                .andDo(print());
    }
}