package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.song.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistSongAssocRepositoryCustom {

    Page<Song> findPlaylistSongList(Playlist playlist, Pageable pageable);

}
