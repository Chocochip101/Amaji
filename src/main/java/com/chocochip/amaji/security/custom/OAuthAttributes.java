package com.chocochip.amaji.security.custom;

import com.chocochip.amaji.exception.ErrorType;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.dto.MemberDto;
import com.chocochip.amaji.oauth.domain.Oauth;
import com.chocochip.amaji.security.exception.AmajiSecurityException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GOOGLE(OAuthProvider.GOOGLE, (attributes) -> {
        Oauth oauth = Oauth.builder()
                .provider(OAuthProvider.GOOGLE).build();
    Member member = Member.builder()
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("picture"))
            .oauth(oauth)
            .build();
    return member;
}),

    NAVER(OAuthProvider.NAVER, (attributes) -> {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        Oauth oauth = Oauth.builder()
                .provider(OAuthProvider.NAVER).build();
        Member member = Member.builder()
                .name((String) response.get("name"))
                .email(((String) response.get("email")))
                .oauth(oauth).build();
        return member;
    }),

    KAKAO(OAuthProvider.KAKAO, (attributes) -> {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        Oauth oauth = Oauth.builder()
                .provider(OAuthProvider.KAKAO).build();
        Member member = Member.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .oauth(oauth)
                .build();

        return member;
    });

    private final OAuthProvider registrationId;
    private final Function<Map<String, Object>, Member> of;

    OAuthAttributes(OAuthProvider provider, Function<Map<String, Object>, Member> of) {
        this.registrationId = provider;
        this.of = of;
    }

    public static Member extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> convertProvider(registrationId).equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }

    public static OAuthProvider convertProvider(String registrationId){
        if(registrationId.equals("kakao")){
            return OAuthProvider.KAKAO;
        }
        else if (registrationId.equals("google")){
            return OAuthProvider.GOOGLE;
        }
        else{
            return OAuthProvider.NAVER;
        }
    }
}
