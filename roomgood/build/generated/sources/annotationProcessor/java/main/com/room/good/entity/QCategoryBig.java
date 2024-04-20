package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryBig is a Querydsl query type for CategoryBig
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryBig extends EntityPathBase<CategoryBig> {

    private static final long serialVersionUID = 2012008322L;

    public static final QCategoryBig categoryBig = new QCategoryBig("categoryBig");

    public final NumberPath<Long> cbno = createNumber("cbno", Long.class);

    public final StringPath cname = createString("cname");

    public final StringPath deliveryFee = createString("deliveryFee");

    public final StringPath refund = createString("refund");

    public QCategoryBig(String variable) {
        super(CategoryBig.class, forVariable(variable));
    }

    public QCategoryBig(Path<? extends CategoryBig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryBig(PathMetadata metadata) {
        super(CategoryBig.class, metadata);
    }

}

