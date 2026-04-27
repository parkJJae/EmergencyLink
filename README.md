# 🚨 EmergencyLink

> 재난 상황에서 해외 체류 한국인을 위한 긴급 연락망 서비스

<br>

## 📌 프로젝트 문서

| 문서 | 링크 |
|------|------|
| 📒 기획 / 설계 (Notion) | [EmergencyLink 노션 페이지](https://www.notion.so/EmergencyLink-API-0ae825f7b37883edaeed013044566034) |
| 📝 개발 기록 (Velog) | [EmergencyLink 시리즈](https://velog.io/@qwg2825/series/EmergencyLink) |
| 🐛 이슈 트래커 | [GitHub Issues](https://github.com/parkJJae/EmergencyLink/issues) |

<br>

## 💡 배경 — 왜 만들었는가

**2024년 1월, 일본 노토반도 M7.5 대지진.**
2011년 도호쿠 이후 최대 규모. 통신망 마비, 관광객 밀집 지역 직격.

**2026년 3월, 중동 분쟁으로 한국인 수백 명 고립.**
카타르·요르단·쿠웨이트 체류 한국인들이 대사관 긴급 지원으로 대피.

두 사건의 공통점 —

> 재난이 터진 그 순간, 사람들이 "대사관 전화번호"를 검색했고
> 가장 먼저 마주한 것은 **"접속 불가"** 화면이었다.

**EmergencyLink는 이 문제를 해결한다.**
10만 명이 동시에 접속해도, 외부 API가 죽어도
단 **50ms 안에** 긴급 연락처를 반환하는 서버를 목표로 한다.

<br>

## 📋 주요 기능

**일반 사용자 (인증 불필요)**
- 국가별 대사관/영사관 정보 조회
- 국가별 현지 긴급번호 조회 (경찰·소방·구급)
- 여행경보 단계 조회 (외교부 실제 4단계 기준)
- 재난 속보 조회 (외부 API 연동)

**관리자 (JWT 인증)**
- 대사관 정보 등록·수정·삭제
- 긴급번호 등록·수정·삭제
- 긴급 공지사항 등록·수정·삭제
- 여행경보 단계 변경

<br>

## 📐 ERD

<img width="1960" height="1101" alt="EmergencyLink ERD" src="https://github.com/user-attachments/assets/2075f791-6ca6-43d3-9fc7-1e9e7df6d3ec" />

<br>

| 테이블 | 설명 | 관계 |
|--------|------|------|
| country | 국가 정보 + 여행경보 단계 | - |
| embassy | 대사관/영사관 | country 1:N |
| emergency_number | 현지 긴급번호 | country 1:N |
| notice | 공지사항 | country 1:N (NULL=전체공지) |
| admin | 관리자 (JWT) | 독립 |

<br>

## 🏗 아키텍처

<img width="753" height="1561" alt="EmergencyLink Architecture" src="https://github.com/user-attachments/assets/cef0d3c1-9479-4a47-9877-9599568881a8" />

### 3겹 방어막 전략

**Layer 1 — Multi-Level Cache (DB 보호)**

| 단계 | 기술 | 응답 속도 | 처리 비율 |
|------|------|----------|----------|
| 1차 | Caffeine (로컬 캐시) | < 1ms | 99% |
| 2차 | Redis (글로벌 캐시) | 1~5ms | 0.9% |
| 3차 | MySQL (DB) | 10~100ms | 0.1% |

**Layer 2 — Circuit Breaker (외부 API 장애 격리)**
- Resilience4j로 외부 API 호출을 감싸 장애 전파(Cascading Failure) 차단
- 3회 연속 실패 시 서킷 OPEN → Fallback 데이터 즉시 반환
- 외부 API가 죽어도 내 서버는 생존

**Layer 3 — CQRS (Read/Write 분리)**
- Master(Write): 관리자 긴급 공지 등록
- Slave(Read): 사용자 대사관 정보 조회
- 10만 명 조회 폭주에도 관리자 쓰기 보장

**Cache Invalidation — Redis Pub/Sub**
- 관리자 데이터 수정 시 Redis Pub/Sub으로 전 서버에 캐시 무효화 신호 발행
- Caffeine + Redis 동시 삭제로 데이터 불일치(Eventual Consistency) 방지

<br>

## 🎯 핵심 기술 목표 (비기능 요구사항)

| 항목 | 목표 |
|------|------|
| 응답 속도 | 평균 50ms 이하 (캐시 적중 기준) |
| 동시 사용자 | 10만 명 처리 |
| DB 직접 조회 | 전체 요청의 1% 미만 |
| 외부 API 장애 시 | Fallback 데이터 즉시 반환 |
| 가용성 | 99.9% 이상 |

<br>

## 🛠 기술 스택

| 분류 | 기술 | 선택 이유 |
|------|------|----------|
| Backend | Spring Boot 3, Java 17 | LTS 안정성, 생태계 |
| Database | MySQL 8 (Master/Slave) | Read/Write 분리로 조회 폭주 대응 |
| Local Cache | Caffeine | Spring Boot 3 공식 지원, 히트율 최고 |
| Global Cache | Redis | 다중 서버 캐시 공유 + Pub/Sub 지원 |
| Resilience | Resilience4j | Hystrix 단종 후 표준, 경량 |
| Test | JMeter | 대규모 동시 접속 부하 테스트 |
| Infra | Docker, Docker Compose | MySQL 이중화 + Redis 환경 일괄 구성 |

<br>

## 📊 성능 테스트 결과

> 🚧 구현 완료 후 JMeter 테스트 결과 업데이트 예정

| 시나리오 | 동시 사용자 | 평균 응답 속도 | DB Hit Rate |
|---------|-----------|-------------|------------|
| 캐시 적용 전 | - | - | - |
| 캐시 적용 후 | - | - | - |
| 외부 API 장애 시 | - | - | - |

<br>

## 🚧 진행 현황

- [x] 요구사항 정의
- [x] ERD 설계
- [x] 아키텍처 설계
- [x] 개발 환경 세팅 (Docker Compose, Spring Boot)
- [ ] Entity 및 Repository 구현
- [ ] Controller / Service 구현
- [ ] JWT 인증
- [ ] 캐시 적용 (Caffeine + Redis)
- [ ] Master/Slave DB 라우팅
- [ ] 서킷브레이커 (Resilience4j)
- [ ] 성능 테스트 (JMeter)