package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1483312463L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCategoryBig categoryBig;

    public final QClubMember clubMember;

    public final StringPath content = createString("content");

    public final NumberPath<Long> discount = createNumber("discount", Long.class);

    public final ListPath<ProductImage, QProductImage> images = this.<ProductImage, QProductImage>createList("images", ProductImage.class, QProductImage.class, PathInits.DIRECT2);

    public final EnumPath<com.room.good.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", com.room.good.constant.ItemSellStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> originalPrice = createNumber("originalPrice", Long.class);

    public final StringPath pname = createString("pname");

    public final NumberPath<Long> pno = createNumber("pno", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> stock = createNumber("stock", Long.class);

    public final StringPath subContent = createString("subContent");

    public final SetPath<Tag, EnumPath<Tag>> tagSet = this.<Tag, EnumPath<Tag>>createSet("tagSet", Tag.class, EnumPath.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryBig = inits.isInitialized("categoryBig") ? new QCategoryBig(forProperty("categoryBig")) : null;
        this.clubMember = inits.isInitialized("clubMember") ? new QClubMember(forProperty("clubMember")) : null;
    }

}

