package com.bssm.interceptor.app.web.dto.song;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.enums.SongGenreType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindSongRs {

    @Schema(description = "곡 id")
    private Long songId;

    @Schema(description = "곡명")
    private String name;

    @Schema(description = "장르")
    private SongGenreType songGenreType;

    @Schema(description = "파일")
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
