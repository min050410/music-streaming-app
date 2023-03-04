package com.bssm.interceptor.db.entity.song;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSong is a Querydsl query type for Song
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSong extends EntityPathBase<Song> {

    private static final long serialVersionUID = 572102932L;

    public static final QSong song = new QSong("song");

    public final StringPath file = createString("file");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final NumberPath<Long> Length = createNumber("Length", Long.class);

    public final StringPath name = createString("name");

    public final SimplePath<com.bssm.interceptor.db.entity.playlist.Playlist> playlist = createSimple("playlist", com.bssm.interceptor.db.entity.playlist.Playlist.class);

    public final EnumPath<com.bssm.interceptor.db.enums.SongGenreType> songGenreType = createEnum("songGenreType", com.bssm.interceptor.db.enums.SongGenreType.class);

    public QSong(String variable) {
        super(Song.class, forVariable(variable));
    }

    public QSong(Path<? extends Song> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSong(PathMetadata metadata) {
        super(Song.class, metadata);
    }

}

