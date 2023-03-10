package com.bssm.interceptor.db.entity.song;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSong is a Querydsl query type for Song
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSong extends EntityPathBase<Song> {

    private static final long serialVersionUID = 572102932L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSong song = new QSong("song");

    public final com.bssm.interceptor.db.entity.common.QBaseTimeEntity _super = new com.bssm.interceptor.db.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath file = createString("file");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final NumberPath<Long> Length = createNumber("Length", Long.class);

    public final com.bssm.interceptor.db.entity.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final com.bssm.interceptor.db.entity.playlist.QPlaylist playlist;

    public final EnumPath<com.bssm.interceptor.db.enums.SongGenreType> songGenreType = createEnum("songGenreType", com.bssm.interceptor.db.enums.SongGenreType.class);

    public QSong(String variable) {
        this(Song.class, forVariable(variable), INITS);
    }

    public QSong(Path<? extends Song> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSong(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSong(PathMetadata metadata, PathInits inits) {
        this(Song.class, metadata, inits);
    }

    public QSong(Class<? extends Song> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.bssm.interceptor.db.entity.member.QMember(forProperty("member")) : null;
        this.playlist = inits.isInitialized("playlist") ? new com.bssm.interceptor.db.entity.playlist.QPlaylist(forProperty("playlist"), inits.get("playlist")) : null;
    }

}

