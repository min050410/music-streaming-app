package com.bssm.interceptor.db.entity.member;

import com.bssm.interceptor.db.enums.MemberRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRoleType memberRoleType;

    @Column(nullable = false)
    private boolean isPremium;



}
