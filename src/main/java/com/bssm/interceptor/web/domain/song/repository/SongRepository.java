package com.bssm.interceptor.web.domain.song.repository;

import com.bssm.interceptor.db.entity.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findSongsByPlaylistId(Long playlistId);

}
