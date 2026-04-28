package com.parkjjae.emergencylink.emergencynumber.entity;

import com.parkjjae.emergencylink.common.entity.BaseEntity;
import com.parkjjae.emergencylink.country.entity.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "emergency_number")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmergencyNumber extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_type", length = 20, nullable = false)
    private EmergencyNumberType numberType;

    @Column(name = "number", length = 20, nullable = false)
    private String number;

    @Column(name = "description", length = 100)
    private String description;

    @Builder
    private EmergencyNumber(Country country, EmergencyNumberType numberType,
                            String number, String description) {
        this.country = country;
        this.numberType = numberType;
        this.number = number;
        this.description = description;
    }
}