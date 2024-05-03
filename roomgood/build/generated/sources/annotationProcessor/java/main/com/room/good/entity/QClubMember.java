package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMember is a Querydsl query type for ClubMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubMember extends EntityPathBase<ClubMember> {

    private static final long serialVersionUID = -2093932912L;

    public static final QClubMember clubMember = new QClubMember("clubMember");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath adress = createString("adress");

    public final StringPath birth = createString("birth");

    public final StringPath code = createString("code");

    public final StringPath company = createString("company");

    public final ListPath<ContactProduct, QContactProduct> contactProducts = this.<ContactProduct, QContactProduct>createList("contactProducts", ContactProduct.class, QContactProduct.class, PathInits.DIRECT2);

    public final StringPath detailaddress = createString("detailaddress");

    public final StringPath email = createString("email");

    public final BooleanPath fromSocial = createBoolean("fromSocial");

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> mileage = createNumber("mileage", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final SetPath<ClubMemberRole, EnumPath<ClubMemberRole>> roleSet = this.<ClubMemberRole, EnumPath<ClubMemberRole>>createSet("roleSet", ClubMemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath streetaddress = createString("streetaddress");

    public QClubMember(String variable) {
        super(ClubMember.class, forVariable(variable));
    }

    public QClubMember(Path<? extends ClubMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubMember(PathMetadata metadata) {
        super(ClubMember.class, metadata);
    }

}

