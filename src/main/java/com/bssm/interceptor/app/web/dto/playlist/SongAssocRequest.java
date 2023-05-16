package com.bssm.interceptor.app.web.dto.playlist;

import com.bssm.interceptor.app.domain.playlist.Playlist;
import com.bssm.interceptor.app.domain.playlist.PlaylistSongAssoc;
import com.bssm.interceptor.app.domain.song.Song;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SongAssocRequest {

    @NotNull
    private Long playlistId;

    @NotNull
    private Long songId;

    public PlaylistSongAssoc toPlaylistSongAssoc(Playlist playlist, Song song) {
        return PlaylistSongAssoc.builder()
            .playlist(playlist)
            .song(song)
            .build();
    }

}
