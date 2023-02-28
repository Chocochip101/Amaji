package com.chocochip.amaji.oauth.service;

import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.member.domain.repository.MemberRepository;
import com.chocochip.amaji.security.custom.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(kakao, google, naver)에서 가져온 유저 정보를 담고있음

        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId(); // OAuth 서비스 이름(ex. kakao, naver, google)

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); // OAuth 로그인 시 키(pk)가 되는 값
        Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth 서비스의 유저 정보들

        Member memberExtracted = OAuthAttributes.extract(registrationId, attributes); // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌

        Member member = saveOrUpdate(memberExtracted);

        Map<String, Object> customAttribute = customAttribute(attributes, userNameAttributeName, member, registrationId);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                customAttribute,
                userNameAttributeName);

    }

    private Map customAttribute(Map attributes, String userNameAttributeName, Member member, String registrationId) {
        Map<String, Object> customAttribute = new LinkedHashMap<>();
        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registrationId);
        customAttribute.put("name", member.getName());
        customAttribute.put("email",  member.getEmail());
        return customAttribute;

    }

    private Member saveOrUpdate(Member member) {

        Member result = memberRepository.findByEmailAndProvider(member.getEmail(), member.getOauth().getProvider())
                .map(m -> m.update(member.getName(), member.getEmail())) // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
                .orElse(Member.builder()
                        .name(member.getName())
                        .nickname(member.getName())
                        .email(member.getEmail())
                        .oauth(member.getOauth())
                        .picture(member.getPicture())
                        .member_role(Role.USER)
                        .build());

        return memberRepository.save(result);
    }


}
