package com.room.good.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerService is a Querydsl query type for CustomerService
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerService extends EntityPathBase<CustomerService> {

    private static final long serialVersionUID = 361253207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerService customerService = new QCustomerService("customerService");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClubMember clubMember;

    public final StringPath cscontent = createString("cscontent");

    public final NumberPath<Long> csno = createNumber("csno", Long.class);

    public final StringPath cstitle = createString("cstitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QOrder1 order1;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QCustomerService(String variable) {
        this(CustomerService.class, forVariable(variable), INITS);
    }

    public QCustomerService(Path<? extends CustomerService> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerService(PathMetadata metadata, PathInits inits) {
        this(CustomerService.class, metadata, inits);
    }

    public QCustomerService(Class<? extends CustomerService> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubMember = inits.isInitialized("clubMember") ? new QClubMember(forProperty("clubMember")) : null;
        this.order1 = inits.isInitialized("order1") ? new QOrder1(forProperty("order1"), inits.get("order1")) : null;
    }

}

