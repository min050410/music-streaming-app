package com.bssm.interceptor.web.domain.song.service;

import com.bssm.interceptor.db.entity.song.Song;
import com.bssm.interceptor.web.domain.song.controller.rs.FindSongRs;
import com.bssm.interceptor.web.domain.song.repository.SongRepository;
import com.bssm.interceptor.web.endpoint.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindSongService {

    private final SongRepository songRepository;

    public ListResponse<Song> findAllSong() {
        List<Song> songList = songRepository.findAll();
        return ListResponse.create(songList);
    }

    public ListResponse<FindSongRs> findSongByPlayListId(Long playlistId) {
        List<Song> songList = songRepository.findSongsByPlaylistId(playlistId);
        List<FindSongRs> songRsList = songList.stream()
                .map(FindSongRs::create)
                .collect(Collectors.toList());
        return ListResponse.create(songRsList);
    }

}
