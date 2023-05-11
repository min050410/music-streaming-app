package com.bssm.interceptor.app.web.controller.playlist;

import com.bssm.interceptor.app.domain.playlist.PlaylistService;
import com.bssm.interceptor.app.web.dto.playlist.AddSongRequest;
import com.bssm.interceptor.app.web.dto.playlist.PlaylistRequest;
import com.bssm.interceptor.app.web.path.ApiPath;
import com.bssm.interceptor.common.config.security.context.LoginMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "플레이리스트")
@RestController
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @Operation(summary = "플레이리스트 생성")
    @PostMapping(ApiPath.PLAYLIST_CREATE)
    public ResponseEntity<Void> createPlayList(@AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody PlaylistRequest playlistRequest) {
        Long playlistId = playlistService.create(loginMember, playlistRequest);
        return ResponseEntity.created(URI.create(ApiPath.PLAYLIST_CREATE + playlistId)).build();
    }

    @Operation(summary = "플레이리스트 곡 추가")
    @PostMapping(ApiPath.PLAYLIST_ADD)
    public ResponseEntity<Void> addSong(@AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody
        AddSongRequest addSongRequest) {
        playlistService.addSong(loginMember, addSongRequest);
        return ResponseEntity.noContent().build();
    }

}
