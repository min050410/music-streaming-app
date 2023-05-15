package com.bssm.interceptor.app.domain.playlist;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongAssocRepository extends JpaRepository<PlaylistSongAssoc, Long>, PlaylistSongAssocRepositoryCustom {

    List<PlaylistSongAssoc> findPlaylistSongAssocsByPlaylist(Playlist playlist);

}

