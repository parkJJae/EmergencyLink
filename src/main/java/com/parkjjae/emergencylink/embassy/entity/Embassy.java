package com.parkjjae.emergencylink.embassy.entity;

import com.parkjjae.emergencylink.common.entity.BaseEntity;
import com.parkjjae.emergencylink.country.entity.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "embassy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Embassy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "embassy_type", length = 20, nullable = false)
    private EmbassyType embassyType;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "operating_hours", length = 100)
    private String operatingHours;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Builder
    private Embassy(Country country, String name, EmbassyType embassyType,
                    String address, String phone, String email,
                    String operatingHours, Boolean isActive) {
        this.country = country;
        this.name = name;
        this.embassyType = embassyType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.operatingHours = operatingHours;
        this.isActive = isActive != null ? isActive : true;
    }
}