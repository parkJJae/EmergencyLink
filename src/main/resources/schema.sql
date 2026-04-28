CREATE TABLE IF NOT EXISTS country (
                                       id           BIGINT       NOT NULL AUTO_INCREMENT,
                                       country_code VARCHAR(2)   NOT NULL COMMENT '국가코드',
    name_ko      VARCHAR(50)  NOT NULL COMMENT '국가명 한국어',
    name_en      VARCHAR(50)  NOT NULL COMMENT '국가명 영어',
    region       VARCHAR(30)  NOT NULL COMMENT '지역',
    alert_level  VARCHAR(20)  NOT NULL COMMENT '여행경보단계',
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_country_code (country_code)
    );

CREATE TABLE IF NOT EXISTS embassy (
                                       id              BIGINT       NOT NULL AUTO_INCREMENT,
                                       country_id      BIGINT       NOT NULL,
                                       name            VARCHAR(100) NOT NULL COMMENT '대사관명',
    embassy_type    VARCHAR(20)  NOT NULL COMMENT 'EMBASSY/CONSULATE',
    address         VARCHAR(255) NOT NULL COMMENT '주소',
    phone           VARCHAR(20)  NOT NULL COMMENT '전화번호',
    email           VARCHAR(100) COMMENT '이메일',
    operating_hours VARCHAR(100) COMMENT '운영시간',
    is_active       BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_embassy_country FOREIGN KEY (country_id) REFERENCES country (id)
    );

CREATE TABLE IF NOT EXISTS emergency_number (
                                                id          BIGINT       NOT NULL AUTO_INCREMENT,
                                                country_id  BIGINT       NOT NULL,
                                                number_type VARCHAR(20)  NOT NULL COMMENT 'POLICE/FIRE/AMBULANCE/EMERGENCY',
    number      VARCHAR(20)  NOT NULL COMMENT '긴급전화번호',
    description VARCHAR(100) COMMENT '번호 설명',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_emergency_number_country FOREIGN KEY (country_id) REFERENCES country (id)
    );

CREATE TABLE IF NOT EXISTS notice (
                                      id          BIGINT       NOT NULL AUTO_INCREMENT,
                                      country_id  BIGINT       COMMENT 'NULL이면 전체공지',
                                      title       VARCHAR(200) NOT NULL COMMENT '공지 제목',
    content     TEXT         NOT NULL COMMENT '공지 내용',
    notice_type VARCHAR(20)  NOT NULL COMMENT 'SYSTEM/DISASTER/EMBASSY',
    priority    VARCHAR(20)  NOT NULL COMMENT 'INFO/WARNING/CRITICAL',
    is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_notice_country FOREIGN KEY (country_id) REFERENCES country (id)
    );

CREATE TABLE IF NOT EXISTS admin (
                                     id            BIGINT       NOT NULL AUTO_INCREMENT,
                                     username      VARCHAR(50)  NOT NULL COMMENT '로그인 아이디',
    password      VARCHAR(255) NOT NULL COMMENT 'bcrypt 해시값',
    name          VARCHAR(50)  NOT NULL COMMENT '관리자 이름',
    refresh_token VARCHAR(500) COMMENT '리프레시 토큰',
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_admin_username (username)
    );