package com.bssm.interceptor.app.domain.song;

import com.bssm.interceptor.app.domain.BaseTimeEntity;
import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.enums.SongGenreType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SongGenreType songGenreType;

    @Column(nullable = false)
    private Long length;

    @Column(nullable = false)
    private String uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Song(String name, String uid, SongGenreType songGenreType, Long length, Member member) {
        this.name = name;
        this.uid = uid;
        this.songGenreType = songGenreType;
        this.length = length;
        this.member = member;
    }

}
