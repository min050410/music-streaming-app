package com.bssm.interceptor.app.web.dto.song;

import com.bssm.interceptor.app.domain.enums.SongGenreType;
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

    @NotBlank
    private SongGenreType genre;

}
