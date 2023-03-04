package com.bssm.interceptor.db.entity.song;
import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.db.entity.playlist.Playlist;
import com.bssm.interceptor.db.enums.SongGenreType;
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
    @Enumerated(EnumType.STRING)
    private SongGenreType songGenreType;

    @Column(nullable = false)
    private Long Length;

    @Column(nullable = false)
    private String file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playlist_id")
    private Playlist playlist;

}
