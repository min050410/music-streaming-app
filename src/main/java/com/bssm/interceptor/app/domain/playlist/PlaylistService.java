package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberService;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final MemberService memberService;

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
        Member member = memberService.findLoginMember(loginMember);

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
        Member member = memberService.findLoginMember(loginMember);

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
        Member member = memberService.findLoginMember(loginMember);
        Playlist playlist = findById(id);

        PageRequest pageRequest = pagination.toPageRequest();
        Page<Song> playlistSongs = playlistSongAssocRepository.findPlaylistSongList(playlist, pageRequest);

        return PlaylistResponse.of(
            member,
            playlist,
            pagination,
            playlistSongs
        );
    }

    /**
     * 플레이리스트 수정
     * @param loginMember
     * @param id
     * @param playlistRequest
     */
    @Transactional
    public void updatePlaylist(LoginMember loginMember, Long id, PlaylistRequest playlistRequest) {
        Member member = memberService.findLoginMember(loginMember);
        Playlist playlist = findById(id);
        Playlist requestPlaylist = playlistRequest.toPlaylist(member);

        if (playlist.isNotPossibleToAccessPlaylist(member)) {
            throw new NotPossibleToAccessPlaylistException();
        }

        playlist.update(requestPlaylist);
    }

    /**
     * 플레이리스트 삭제
     * @param loginMember
     * @param id
     */
    @Transactional
    public void deletePlaylist(LoginMember loginMember, Long id) {
        Member member = memberService.findLoginMember(loginMember);
        Playlist playlist = findById(id);

        if (playlist.isNotPossibleToAccessPlaylist(member)) {
            throw new NotPossibleToAccessPlaylistException();
        }

        List<PlaylistSongAssoc> playlistSongAssoc = playlistSongAssocRepository.findPlaylistSongAssocsByPlaylist(playlist);

        playlistSongAssocRepository.deleteAll(playlistSongAssoc);
        playlistRepository.delete(playlist);
    }

    private Playlist findById(Long id) {
        return playlistRepository.findById(id)
            .orElseThrow(PlaylistNotFoundException::new);
    }

}
