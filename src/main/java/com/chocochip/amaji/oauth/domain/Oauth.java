package com.chocochip.amaji.oauth.domain;

import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.security.custom.OAuthAttributes;
import com.chocochip.amaji.security.custom.OAuthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Oauth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    @OneToOne(mappedBy = "oauth", fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Oauth(Long id, Member member, String refreshToken, OAuthProvider provider) {
        this.id = id;
        this.member = member;
        this.refreshToken = refreshToken;
        this.provider = provider;
    }
}


