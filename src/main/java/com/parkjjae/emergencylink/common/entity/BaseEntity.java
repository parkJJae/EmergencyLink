package com.parkjjae.emergencylink.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속받는 자식들은 이 안의 필드를 컬럼으로 가져간다.
@EntityListeners(AuditingEntityListener.class) // 엔티티 생성/수정 이벤트를 감지하는 리스너
public abstract class BaseEntity {

    @CreatedDate // INSERT 시점에 자동으로 현재 시간 채움
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // UPDATE 시점에 자동으로 현재 시간 갱신
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}