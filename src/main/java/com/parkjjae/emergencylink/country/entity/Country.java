package com.parkjjae.emergencylink.country.entity;

import com.parkjjae.emergencylink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "country")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", length = 2, nullable = false, unique = true)
    private String countryCode;

    @Column(name = "name_ko", length = 50, nullable = false)
    private String nameKo;

    @Column(name = "name_en", length = 50, nullable = false)
    private String nameEn;

    @Column(name = "region", length = 30, nullable = false)
    private String region;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_level", length = 20, nullable = false)
    private AlertLevel alertLevel;

    @Builder
    private Country(String countryCode, String nameKo, String nameEn, String region, AlertLevel alertLevel) {
        this.countryCode = countryCode;
        this.nameKo = nameKo;
        this.nameEn = nameEn;
        this.region = region;
        this.alertLevel = alertLevel;
    }
}