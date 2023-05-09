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

    public Song toSong(Member member) {
        // TODO: file 로직 추가시 수정
        return Song.builder()
            .name(name)
            .file("hi")
            .length(20L)
            .songGenreType(genre)
            .member(member)
            .build();
    }

}
