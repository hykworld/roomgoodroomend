package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCsImage is a Querydsl query type for CsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCsImage extends EntityPathBase<CsImage> {

    private static final long serialVersionUID = -1470516437L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCsImage csImage = new QCsImage("csImage");

    public final QClubMember clubMember;

    public final StringPath csimgName = createString("csimgName");

    public final NumberPath<Long> csino = createNumber("csino", Long.class);

    public final StringPath csipath = createString("csipath");

    public final StringPath csiuuid = createString("csiuuid");

    public QCsImage(String variable) {
        this(CsImage.class, forVariable(variable), INITS);
    }

    public QCsImage(Path<? extends CsImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCsImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCsImage(PathMetadata metadata, PathInits inits) {
        this(CsImage.class, metadata, inits);
    }

    public QCsImage(Class<? extends CsImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubMember = inits.isInitialized("clubMember") ? new QClubMember(forProperty("clubMember")) : null;
    }

}

