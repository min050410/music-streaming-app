package com.bssm.interceptor.app.domain.song;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberService;
import com.bssm.interceptor.app.domain.playlist.PlaylistRepository;
import com.bssm.interceptor.app.web.common.response.ListResponse;
import com.bssm.interceptor.app.web.dto.song.SongRequest;
import com.bssm.interceptor.common.config.security.context.LoginMember;
import com.bssm.interceptor.common.util.FileUploadUtil;
import com.bssm.interceptor.common.util.StreamingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final MemberService memberService;

    /**
     * 곡 업로드
     *
     * @param loginMember
     * @param songRequest
     * @param file
     * @return songId
     */
    @Transactional
    public Long create(LoginMember loginMember, SongRequest songRequest, MultipartFile file) {
        Member member = memberService.findLoginMember(loginMember);
        String uid = FileUploadUtil.uploadMp3(file);

        Long contentLength = StreamingUtil.getContentLength(uid);
        Song song = songRepository.save(songRequest.toSong(member, uid, contentLength));
        return song.getId();
    }

    @Transactional(readOnly = true)
    public ListResponse<Song> findAllSong() {
        List<Song> songList = songRepository.findAll();
        return ListResponse.create(songList);
    }

//    @Transactional(readOnly = true)
//    public ListResponse<FindSongResponse> findSongByPlayListId(Long playlistId) {
//
//        Playlist playlist = playlistRepository.findById(playlistId)
//            .orElseThrow(PlaylistNotFoundException::new);
//
//        List<Song> songList = playlist.getSong();
//        List<FindSongResponse> songRsList = songList.stream()
//            .map(FindSongResponse::create)
//            .collect(Collectors.toList());
//        return ListResponse.create(songRsList);
//    }

}
