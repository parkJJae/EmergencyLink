package com.parkjjae.emergencylink.country.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 여행경보 단계
public enum AlertLevel {

    LEVEL_1(1, "여행유의", "조심해서 가세요"),
    LEVEL_2(2, "여행자제", "가능하면 가지 마세요"),
    LEVEL_3(3, "출국권고", "지금 있으면 나오세요"),
    LEVEL_4(4, "여행금지", "법적으로 방문 금지");

    private final int level; // 외교부 공식 단계 숫자 (1~4)
    private final String name;
    private final String description;
}