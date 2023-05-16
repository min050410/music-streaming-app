package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.song.Song;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongAssocRepository extends JpaRepository<PlaylistSongAssoc, Long>, PlaylistSongAssocRepositoryCustom {

    List<PlaylistSongAssoc> findPlaylistSongAssocsByPlaylist(Playlist playlist);

    Optional<PlaylistSongAssoc> findPlaylistSongAssocsByPlaylistAndSong(Playlist playlist, Song song);

}

