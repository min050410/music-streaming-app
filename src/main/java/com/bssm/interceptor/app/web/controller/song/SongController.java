package com.bssm.interceptor.app.web.controller.song;

import com.bssm.interceptor.common.config.security.context.LoginMember;
import com.bssm.interceptor.common.util.FileValidateUtil;
import com.bssm.interceptor.common.util.StreamingUtil;
import java.net.URI;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.song.SongService;
import com.bssm.interceptor.app.web.common.response.ListResponse;
import com.bssm.interceptor.app.web.dto.song.SongRequest;
import com.bssm.interceptor.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "곡")
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @Operation(summary = "곡 생성")
    @PostMapping(ApiPath.SONG_CREATE)
    public ResponseEntity<Void> createSong(
        @AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestPart(value = "request") SongRequest songRequest,
        @RequestPart(value = "file") MultipartFile file
    ) {
        FileValidateUtil.mp3ValidationCheck(file);
        Long songId = songService.create(loginMember, songRequest, file);
        return ResponseEntity.created(URI.create(ApiPath.SONG_CREATE + songId)).build();
    }

    @Operation(summary = "모든 곡 불러오기")
    @GetMapping(ApiPath.SONG)
    public ListResponse<Song> findAllSong() {
        return songService.findAllSong();
    }

    @Operation(summary = "곡 재생")
    @GetMapping(ApiPath.SONG_PLAY)
    public ResponseEntity<ResourceRegion> playSong(
        @RequestHeader HttpHeaders headers,
        @PathVariable("uid") String uid) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
            .contentType(MediaTypeFactory.getMediaType(StreamingUtil.getResource(uid))
                .orElse(MediaType.APPLICATION_OCTET_STREAM))
            .header("Accept-Ranges", "bytes")
            .eTag(StreamingUtil.getPath())
            .body(StreamingUtil.streamMp3(uid, headers));
    }


}
