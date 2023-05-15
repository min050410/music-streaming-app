package com.bssm.interceptor.app.web.dto.playlist;

import com.bssm.interceptor.app.domain.playlist.Playlist;
import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.web.common.response.PagedResponse;
import com.bssm.interceptor.app.web.common.response.Pagination;
import com.bssm.interceptor.app.web.dto.member.MemberResponse;
import com.bssm.interceptor.app.web.dto.song.SongResponse;
import com.bssm.interceptor.common.util.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PlaylistResponse {

    private long id;

    private String playlistName;

    private String createdDate;

    private MemberResponse writer;

    private PagedResponse<SongResponse> songs;

    public static PlaylistResponse of(Playlist playlist, Pagination pagination, Page<Song> playlistSongs) {
        return new PlaylistResponse(
            playlist.getId(),
            playlist.getName(),
            DateUtil.formatDateYYYYMMDD(playlist.getCreateDate()),
            MemberResponse.of(playlist.getMember()),
            SongResponse.pagedListOf(pagination, playlistSongs)
        );
    }

}
