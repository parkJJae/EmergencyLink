package com.parkjjae.emergencylink.embassy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmbassyType {

    EMBASSY("대사관"),
    CONSULATE("영사관");

    private final String description;
}