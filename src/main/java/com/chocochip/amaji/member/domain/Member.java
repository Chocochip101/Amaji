package com.chocochip.amaji.member.domain;

import com.chocochip.amaji.global.entity.BaseEntity;
import com.chocochip.amaji.global.util.Role;
import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import com.chocochip.amaji.oauth.domain.AuthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {
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

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;


    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;


    //=========  일대다   =========//
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberRestaurant> memberRestaurantList = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String nickname, String email, String picture, Role member_role, boolean isDeleted, String refreshToken, AuthProvider authProvider) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.picture = picture;
        this.member_role = member_role;
        this.isDeleted = isDeleted;
        this.refreshToken = refreshToken;
        this.authProvider = authProvider;
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
    

}
