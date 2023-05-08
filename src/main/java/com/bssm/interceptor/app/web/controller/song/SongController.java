package com.bssm.interceptor.app.web.controller.song;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.web.dto.song.FindSongRs;
import com.bssm.interceptor.app.domain.song.FindSongService;
import com.bssm.interceptor.app.web.common.response.ListResponse;
import com.bssm.interceptor.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "곡")
@RestController
@RequiredArgsConstructor
public class SongController {

    private final FindSongService findSongService;

    @Operation(summary = "모든 곡 불러오기")
    @GetMapping(ApiPath.SONG)
    public ListResponse<Song> findAllSong() {
        return findSongService.findAllSong();
    }

    @Operation(summary = "플레이리스트 곡 조회")
    @GetMapping(ApiPath.SONG_BY_ID)
    public ListResponse<FindSongRs> findSongByPlayListId(
        @PathVariable("playlist-id") Long playlistId) {
        return findSongService.findSongByPlayListId(playlistId);
    }

}
