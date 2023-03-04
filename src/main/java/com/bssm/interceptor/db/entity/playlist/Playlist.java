package com.bssm.interceptor.db.entity.playlist;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.db.entity.song.Song;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playlist_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="song_id")
    private List<Song> song = new ArrayList<>();

}
