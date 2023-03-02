package com.bssm.interceptor.db.entity.playlist;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.db.entity.song.Song;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playlist_id")
    private Long Id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany
    @JoinColumn(name="song_id")
    private List<Song> song = new ArrayList<>();

}
