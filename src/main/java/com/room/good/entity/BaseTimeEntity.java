package com.room.good.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
// Auditing 적용위해 추가 // 공통 매핑정보가 필요할 때 사용하는 어노테이션으로 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
// 엔티티가 생성되어 저장될 때 시간을 자동으로 저장 // 변경할 때도 자동으로 저장 // 그래서 다른데서 코드 쓸 필요 없음
public abstract class BaseTimeEntity {
    @CreatedDate // 엔티티가 생성될떄 자동으로 생성 시간이 저장
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate // 수정될때 자동으로 수정자가 저장
    private LocalDateTime updateTime;

}
