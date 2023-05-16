package com.bssm.interceptor.app.domain.playlist;

import com.bssm.interceptor.app.domain.song.Song;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class PlaylistSongAssocRepositoryImpl implements PlaylistSongAssocRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Song> findPlaylistSongList(Playlist playlist, Pageable pageable) {
        List<Song> contents = jpaQueryFactory
            .selectFrom(QPlaylistSongAssoc.playlistSongAssoc)
            .select(QPlaylistSongAssoc.playlistSongAssoc.song)
            .where(
                playlistEq(playlist)
            )
            .orderBy(QPlaylistSongAssoc.playlistSongAssoc.createDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Song> countQuery = jpaQueryFactory
            .selectFrom(QPlaylistSongAssoc.playlistSongAssoc)
            .select(QPlaylistSongAssoc.playlistSongAssoc.song)
            .where(playlistEq(playlist));

        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetch().size());
    }

    private BooleanExpression playlistEq(Playlist playlist) {
        if (ObjectUtils.isEmpty(playlist)) {
            return null;
        }
        return QPlaylistSongAssoc.playlistSongAssoc.playlist.in(playlist);
    }

}
