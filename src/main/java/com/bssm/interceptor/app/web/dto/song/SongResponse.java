package com.bssm.interceptor.app.web.dto.song;

import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.web.common.response.PagedResponse;
import com.bssm.interceptor.app.web.common.response.Pagination;
import com.bssm.interceptor.app.web.dto.member.MemberResponse;
import com.bssm.interceptor.common.util.DateUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SongResponse {

    private long id;

    private String name;

    private String createDate;

    private Long length;

    private String file;

    private MemberResponse writer;

    public static SongResponse of(Song song) {
        return new SongResponse(
            song.getId(),
            song.getName(),
            DateUtil.formatDateYYYYMMDD(song.getCreateDate()),
            song.getLength(),
            song.getUid(),
            MemberResponse.of(song.getMember())
        );
    }

    public static List<SongResponse> listOf(List<Song> songs) {
        return songs.stream()
            .map(SongResponse::of)
            .collect(Collectors.toList());
    }

    public static PagedResponse<SongResponse> pagedListOf(Pagination pagination, Page<Song> songs) {
        List<SongResponse> songList = songs.stream()
            .map(SongResponse::of)
            .collect(Collectors.toList());

        pagination.setTotalCount(songs.getTotalElements());
        pagination.setTotalPages(songs.getTotalPages());

        return PagedResponse.of(
            pagination,
            songList
        );
    }

}
