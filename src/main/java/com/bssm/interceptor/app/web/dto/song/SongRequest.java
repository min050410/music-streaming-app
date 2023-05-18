package com.bssm.interceptor.app.web.dto.song;

import com.bssm.interceptor.app.domain.enums.SongGenreType;
import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.common.decorator.validator.ValidEnum;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SongRequest {

    @NotBlank
    private String name;

    @ValidEnum(enumClass = SongGenreType.class)
    private SongGenreType genre;

    public Song toSong(Member member, String uid, Long length) {
        return Song.builder()
            .name(name)
            .uid(uid)
            .length(length)
            .songGenreType(genre)
            .member(member)
            .build();
    }

}
