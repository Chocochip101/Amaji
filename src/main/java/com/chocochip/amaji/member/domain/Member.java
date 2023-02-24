package com.chocochip.amaji.member.domain;

import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.oauth.domain.Oauth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_picture")
    private String picture;


    @Enumerated(EnumType.STRING)
    private Role member_role = Role.GUEST;

    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "oauth_id")
    private Oauth oauth;

    @Builder
    public Member(Long id, String name, String nickname, String email, String picture, Role member_role, boolean isDeleted, Oauth oauth) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.picture = picture;
        this.member_role = member_role;
        this.isDeleted = isDeleted;
        this.oauth = oauth;
    }


    public boolean hasNickname() {
        return !Objects.isNull(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    public String getRoleKey() {
        return this.member_role.getKey();
    }

    public Member update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }

}
