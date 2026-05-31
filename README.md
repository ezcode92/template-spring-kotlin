# Spring Kotlin Template

Kotlin, Spring Boot 3, Java 21 기반의 재사용 가능한 백엔드 템플릿입니다.

## 주요 구성

- Gradle Kotlin DSL
- Spring Boot 3, Kotlin, Java 21
- PostgreSQL, Flyway
- Docker Compose 기반 로컬 환경
- Spring Security 기본 설정
- OAuth2 소셜 로그인 설정 골격: Google, Kakao, Naver
- 내부 JWT 발급/검증 구조 골격
- 전역 예외 처리와 표준 API 응답
- Actuator 및 `/api/v1/health` 헬스 체크
- 구조화 로그 설정
- 모듈러 모놀리스 지향 패키지 구조

## 실행

```bash
cp .env.example .env
docker compose up --build
```

헬스 체크:

```bash
curl http://localhost:8080/api/v1/health
```

## 로컬 빌드

Gradle 설치 없이 Wrapper로 빌드할 수 있습니다.

```bash
./gradlew build
```

## 패키지 구조

```text
backend/src/main/kotlin/com/template
├── auth
│   ├── application
│   ├── domain
│   ├── infrastructure
│   └── presentation
├── common
├── config
├── health
└── ping
    ├── application
    ├── domain
    ├── infrastructure
    └── presentation
```

## 운영 전 필수 작업

- `JWT_SECRET`을 충분히 긴 운영 비밀값으로 교체합니다.
- OAuth2 provider별 client id/secret을 환경 변수로 주입합니다.
- `JwtTokenService`와 `JwtAuthenticationFilter`의 TODO를 실제 서명 검증/발급 로직으로 구현합니다.
- 운영 프로필에서는 로그, DB 계정, 네트워크 접근 정책을 환경별로 분리합니다.
