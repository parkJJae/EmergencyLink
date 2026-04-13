# 🚨 EmergencyLink

> 재난 상황에서 해외 체류 한국인을 위한 긴급 연락망 서비스

<br>

## 📌 프로젝트 문서

| 문서 | 링크 |
|------|------|
| 📒 기획 / 설계 (Notion) | [EmergencyLink 노션 페이지](https://www.notion.so/EmergencyLink-API-0ae825f7b37883edaeed013044566034) |
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

> 🚧 설계 진행 중 — 확정 시 업데이트 예정

| 분류 | 기술 |
|------|------|
| Backend | Spring Boot 3, Java 17 |
| Database | MySQL 8 (Master/Slave) |
| Cache | Redis, Caffeine |
| Resilience | Resilience4j |
| Test | JMeter |
| Infra | Docker, Docker Compose |

<br>

## 🚧 진행 현황

- [ ] 요구사항 정의
- [ ] ERD 설계
- [ ] 아키텍처 설계
- [ ] 구현
- [ ] 성능 테스트 (JMeter)
