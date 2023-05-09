package com.bssm.interceptor.app.domain.song;

import com.bssm.interceptor.app.web.dto.song.FindSongResponse;
import com.bssm.interceptor.app.web.common.response.ListResponse;
import com.bssm.interceptor.app.web.dto.song.SongRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public Long create(SongRequest body) {
        return null;
    }

    @Transactional(readOnly = true)
    public ListResponse<Song> findAllSong() {
        List<Song> songList = songRepository.findAll();
        return ListResponse.create(songList);
    }

    @Transactional(readOnly = true)
    public ListResponse<FindSongResponse> findSongByPlayListId(Long playlistId) {
        List<Song> songList = songRepository.findSongsByPlaylistId(playlistId);
        List<FindSongResponse> songRsList = songList.stream()
            .map(FindSongResponse::create)
            .collect(Collectors.toList());
        return ListResponse.create(songRsList);
    }
}
