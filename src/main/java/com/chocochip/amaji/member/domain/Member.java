package com.chocochip.amaji.member.domain;

import com.chocochip.amaji.global.util.Role;
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

    private String member_name;
    @Column(nullable = false)
    private String member_nickname;

    private String member_email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role member_role;

    public boolean hasNickname() {
        return !Objects.isNull(member_nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }
}
