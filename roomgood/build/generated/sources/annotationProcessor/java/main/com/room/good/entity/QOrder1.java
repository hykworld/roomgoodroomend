package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder1 is a Querydsl query type for Order1
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder1 extends EntityPathBase<Order1> {

    private static final long serialVersionUID = -396749245L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder1 order1 = new QOrder1("order1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClubMember clubMember;

    public final DateTimePath<java.util.Date> desireddate = createDateTime("desireddate", java.util.Date.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> ono = createNumber("ono", Long.class);

    public final StringPath recipient = createString("recipient");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath status = createString("status");

    public QOrder1(String variable) {
        this(Order1.class, forVariable(variable), INITS);
    }

    public QOrder1(Path<? extends Order1> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder1(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder1(PathMetadata metadata, PathInits inits) {
        this(Order1.class, metadata, inits);
    }

    public QOrder1(Class<? extends Order1> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubMember = inits.isInitialized("clubMember") ? new QClubMember(forProperty("clubMember")) : null;
    }

}

