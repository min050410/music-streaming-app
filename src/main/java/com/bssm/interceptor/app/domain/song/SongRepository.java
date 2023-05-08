package com.bssm.interceptor.app.domain.song;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findSongsByPlaylistId(Long playlistId);

}
