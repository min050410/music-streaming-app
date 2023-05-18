package com.bssm.interceptor.app.web.dto.song;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.enums.SongGenreType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindSongResponse {

    @Schema(description = "곡 id")
    private Long songId;

    @Schema(description = "곡명")
    private String name;

    @Schema(description = "장르")
    private SongGenreType songGenreType;

    @Schema(description = "파일")
    private String file;

    public static FindSongResponse create(Song song) {
        FindSongResponse rs = new FindSongResponse();
        rs.songId = song.getId();
        rs.name = song.getName();
        rs.file = song.getUid();
        rs.songGenreType = song.getSongGenreType();
        return rs;
    }

}
