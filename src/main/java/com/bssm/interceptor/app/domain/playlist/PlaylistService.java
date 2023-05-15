package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberRepository;
import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.song.SongRepository;
import com.bssm.interceptor.app.web.common.response.Pagination;
import com.bssm.interceptor.app.web.dto.playlist.AddSongRequest;
import com.bssm.interceptor.app.web.dto.playlist.PlaylistRequest;
import com.bssm.interceptor.app.web.dto.playlist.PlaylistResponse;
import com.bssm.interceptor.common.config.security.context.LoginMember;
import com.bssm.interceptor.common.exception.NotPossibleToAccessPlaylistException;
import com.bssm.interceptor.common.exception.PlaylistNotFoundException;
import com.bssm.interceptor.common.exception.SongNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final MemberRepository memberRepository;

    private final PlaylistRepository playlistRepository;

    private final SongRepository songRepository;

    private final PlaylistSongAssocRepository playlistSongAssocRepository;

    /**
     * 플레이리스트 생성
     * @param loginMember
     * @param playlistRequest
     * @return Long
     */
    @Transactional
    public Long create(LoginMember loginMember, PlaylistRequest playlistRequest) {
        Member member = memberRepository.getReferenceById(loginMember.getId());

        Playlist playlist = playlistRepository.save(playlistRequest.toPlaylist(member));
        return playlist.getId();
    }

    /**
     * 플레이리스트 곡 추가
     * @param loginMember
     * @param addSongRequest
     */
    @Transactional
    public void addSong(LoginMember loginMember, AddSongRequest addSongRequest) {
        Member member = memberRepository.getReferenceById(loginMember.getId());

        Song song = songRepository.findById(addSongRequest.getSongId())
            .orElseThrow(SongNotFoundException::new);

        Playlist playlist = findById(addSongRequest.getPlaylistId());

        if (playlist.isNotPossibleToAccessPlaylist(member)) {
            throw new NotPossibleToAccessPlaylistException();
        }

        playlistSongAssocRepository.save(addSongRequest.toPlaylistSongAssoc(playlist, song));
    }

    /**
     * 플레이리스트 조회
     * @param loginMember
     * @param pagination
     * @param id
     * @return PlaylistResponse
     */
    @Transactional(readOnly = true)
    public PlaylistResponse findPlayList(LoginMember loginMember, Pagination pagination, Long id) {
        Playlist playlist = findById(id);

        PageRequest pageRequest = pagination.toPageRequest();
        Page<Song> playlistSongs = playlistSongAssocRepository.findPlaylistSongList(playlist, pageRequest);

        return PlaylistResponse.of(
            playlist,
            pagination,
            playlistSongs
        );
    }

    private Playlist findById(Long id) {
        return playlistRepository.findById(id)
            .orElseThrow(PlaylistNotFoundException::new);
    }

}
