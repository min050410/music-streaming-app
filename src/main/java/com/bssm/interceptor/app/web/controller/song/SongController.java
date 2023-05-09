package com.bssm.interceptor.app.web.controller.song;

import com.bssm.interceptor.common.config.security.context.LoginMember;
import java.net.URI;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.song.SongService;
import com.bssm.interceptor.app.web.common.response.ListResponse;
import com.bssm.interceptor.app.web.dto.song.SongRequest;
import com.bssm.interceptor.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "곡")
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @Operation(summary = "곡 생성")
    @PostMapping(ApiPath.SONG_CREATE)
    public ResponseEntity<Void> createSong(@AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody SongRequest songRequest) {
        Long songId = songService.create(loginMember, songRequest);
        return ResponseEntity.created(URI.create(ApiPath.SONG_CREATE + songId)).build();
    }

    @Operation(summary = "모든 곡 불러오기")
    @GetMapping(ApiPath.SONG)
    public ListResponse<Song> findAllSong() {
        return songService.findAllSong();
    }

//    @Operation(summary = "플레이리스트 곡 조회")
//    @GetMapping(ApiPath.SONG_BY_ID)
//    public ListResponse<FindSongResponse> findSongByPlayListId(
//        @PathVariable("playlist-id") Long playlistId) {
//        return songService.findSongByPlayListId(playlistId);
//    }

}
