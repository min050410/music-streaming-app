package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberRepository;
import com.bssm.interceptor.app.domain.song.Song;
import com.bssm.interceptor.app.domain.song.SongRepository;
import com.bssm.interceptor.app.web.dto.playlist.AddSongRequest;
import com.bssm.interceptor.app.web.dto.playlist.PlaylistRequest;
import com.bssm.interceptor.common.config.security.context.LoginMember;
import com.bssm.interceptor.common.exception.NotPossibleToAccessPlaylistException;
import com.bssm.interceptor.common.exception.PlaylistNotFoundException;
import com.bssm.interceptor.common.exception.SongNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final MemberRepository memberRepository;

    private final PlaylistRepository playlistRepository;

    private final SongRepository songRepository;

    private final PlaylistSongAssocRepository playlistSongAssocRepository;

    public Long create(LoginMember loginMember, PlaylistRequest playlistRequest) {
        Member member = memberRepository.getReferenceById(loginMember.getId());

        Playlist playlist = playlistRepository.save(playlistRequest.toPlaylist(member));
        return playlist.getId();
    }

    public void addSong(LoginMember loginMember, AddSongRequest addSongRequest) {
        Member member = memberRepository.getReferenceById(loginMember.getId());

        Song song = songRepository.findById(addSongRequest.getSongId())
            .orElseThrow(SongNotFoundException::new);

        Playlist playlist = playlistRepository.findById(addSongRequest.getPlaylistId())
            .orElseThrow(PlaylistNotFoundException::new);

        if (playlist.isNotPossibleToAccessPlaylist(member)) {
            throw new NotPossibleToAccessPlaylistException();
        }

        playlistSongAssocRepository.save(addSongRequest.toPlaylistSongAssoc(playlist, song));
    }

}
