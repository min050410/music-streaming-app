package com.bssm.interceptor.db.entity.song;

import com.bssm.interceptor.db.entity.playlist.Playlist;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="song_id")
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long Length;

    @Column(nullable = false)
    private String file;

}
