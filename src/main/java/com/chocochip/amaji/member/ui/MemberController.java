package com.chocochip.amaji.member.ui;

import com.chocochip.amaji.member.service.MemberService;
import com.chocochip.amaji.member.ui.dto.MemberNicknameRequest;
import com.chocochip.amaji.member.ui.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;
    @PatchMapping("/{user-id}/nickname")
    public ResponseEntity<Void> changeNickname(
            @PathVariable("user-id") Long memberId,
            @RequestBody @Valid MemberNicknameRequest memberNicknameRequest){
        memberService.changeMemberNickname(memberId, memberNicknameRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<MemberResponse> getOneMember(
            @PathVariable("user-id") Long memberId){
        MemberResponse memberResponse = memberService.findOneMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }
}
