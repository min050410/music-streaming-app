package com.bssm.interceptor.web.domain.song.controller.rs;

import com.bssm.interceptor.db.entity.song.Song;
import com.bssm.interceptor.db.enums.SongGenreType;
import lombok.Data;

@Data
public class FindSongRs {
    private Long songId;

    private String name;

    private SongGenreType songGenreType;

    private String file;

    public static FindSongRs create(Song song) {
        FindSongRs rs = new FindSongRs();
        rs.songId = song.getId();
        rs.name = song.getName();
        rs.file = song.getFile();
        rs.songGenreType = song.getSongGenreType();
        return rs;
    }

}
