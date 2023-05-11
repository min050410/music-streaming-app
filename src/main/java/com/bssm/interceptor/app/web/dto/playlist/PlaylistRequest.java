package com.bssm.interceptor.app.web.dto.playlist;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.playlist.Playlist;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PlaylistRequest {

    @NotBlank
    private String name;

    public Playlist toPlaylist(Member member) {
        return Playlist.builder()
            .name(name)
            .member(member)
            .build();
    }

}
