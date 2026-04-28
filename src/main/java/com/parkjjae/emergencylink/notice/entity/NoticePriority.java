package com.parkjjae.emergencylink.notice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticePriority {

    INFO("일반 정보"),
    WARNING("주의"),
    CRITICAL("긴급");

    private final String description;
}