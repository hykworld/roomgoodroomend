package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryMiddle is a Querydsl query type for CategoryMiddle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryMiddle extends EntityPathBase<CategoryMiddle> {

    private static final long serialVersionUID = -508731437L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryMiddle categoryMiddle = new QCategoryMiddle("categoryMiddle");

    public final QCategoryBig categoryBig;

    public final StringPath cmname = createString("cmname");

    public final NumberPath<Long> cmno = createNumber("cmno", Long.class);

    public QCategoryMiddle(String variable) {
        this(CategoryMiddle.class, forVariable(variable), INITS);
    }

    public QCategoryMiddle(Path<? extends CategoryMiddle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryMiddle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryMiddle(PathMetadata metadata, PathInits inits) {
        this(CategoryMiddle.class, metadata, inits);
    }

    public QCategoryMiddle(Class<? extends CategoryMiddle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryBig = inits.isInitialized("categoryBig") ? new QCategoryBig(forProperty("categoryBig")) : null;
    }

}

