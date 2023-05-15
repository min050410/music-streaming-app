package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.BaseTimeEntity;
import com.bssm.interceptor.app.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    private Playlist(String name, Member member) {
        this.name = name;
        this.member = member;
    }

    boolean isNotPossibleToAccessPlaylist(Member member) {
        Long loginMemberId = member.getId();
        Long memberId = this.member.getId();
        return !memberId.equals(loginMemberId);
    }

    public void update(Playlist requestPlaylist) {
        this.name = requestPlaylist.name;
    }

}
