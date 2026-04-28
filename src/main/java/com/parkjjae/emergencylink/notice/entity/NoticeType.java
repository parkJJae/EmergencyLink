package com.parkjjae.emergencylink.notice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {

    SYSTEM("시스템 공지"),
    DISASTER("재난 속보"),
    EMBASSY("대사관 공지");

    private final String description;
}