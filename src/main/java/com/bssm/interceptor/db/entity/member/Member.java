package com.bssm.interceptor.db.entity.member;

import com.bssm.interceptor.db.entity.common.BaseTimeEntity;
import com.bssm.interceptor.db.enums.MemberRoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRoleType memberRoleType;

    @Column(nullable = false)
    private boolean isPremium;

    public static Member create(String email, String nickname, String password, MemberRoleType role) {
        Member member = new Member();
        member.email = email;
        member.nickname = nickname;
        member.password = password;
        member.memberRoleType = role;
        return member;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }


}
