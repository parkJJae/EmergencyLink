package com.parkjjae.emergencylink.notice.entity;

import com.parkjjae.emergencylink.common.entity.BaseEntity;
import com.parkjjae.emergencylink.country.entity.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type", length = 20, nullable = false)
    private NoticeType noticeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 20, nullable = false)
    private NoticePriority priority;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Builder
    private Notice(Country country, String title, String content,
                   NoticeType noticeType, NoticePriority priority, Boolean isActive) {
        this.country = country;
        this.title = title;
        this.content = content;
        this.noticeType = noticeType;
        this.priority = priority;
        this.isActive = isActive != null ? isActive : true;
    }
}