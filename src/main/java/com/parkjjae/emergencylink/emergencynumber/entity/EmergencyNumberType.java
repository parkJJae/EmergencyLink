package com.parkjjae.emergencylink.emergencynumber.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmergencyNumberType {

    POLICE("경찰"),
    FIRE("소방"),
    AMBULANCE("구급"),
    EMERGENCY("통합긴급");

    private final String description;
}