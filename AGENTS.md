# AGENTS.md

## 템플릿 개요

이 프로젝트는 Kotlin + Spring Boot 기반 SaaS/Backend 템플릿이다.

재사용 가능한 production-ready backend template 생성을 목표로 한다.

설명, README, 문서, 코드 주석은 한글 사용을 기본으로 한다.

---

# Core Stack

- Use Kotlin
- Use Spring Boot 3
- Use Java 21
- Use Gradle Kotlin DSL
- Use PostgreSQL
- Use Docker Compose

## 추가 설명

- Backend 기본 언어는 Kotlin 사용
- Database는 PostgreSQL 기본 사용
- 로컬 개발환경은 Docker Compose 사용

---

# Architecture

- Prefer modular monolith architecture
- Use feature-based package structure
- Keep domain boundaries clear
- Avoid premature microservices
- Prefer simplicity over overengineering

## 추가 설명

초기 SaaS 개발 생산성을 우선한다.

도메인 단위로 패키지를 구성한다.

예시:

- auth
- user
- notification
- common

---

# Package Structure

- Avoid package-by-layer only
- Group related logic by feature
- Keep common modules separated

## 예시 구조

src/main/kotlin/com/example/template/

- auth/
- user/
- common/
- config/
- infrastructure/

---

# API Rules

- Use REST API first
- Use /api/v1 prefix
- Use JWT authentication
- Return consistent API responses
- Use proper HTTP status codes
- Validate all request inputs

## 추가 설명

- Entity 직접 반환 금지
- Validation 적용 필수
- API 응답 구조 통일

---

# Security

- Never hardcode secrets
- Use environment variables
- Sanitize external inputs
- Avoid insecure defaults

## 추가 설명

- 비밀번호/API Key 하드코딩 금지
- 민감정보 로그 출력 금지

---

# Database Rules

- Use Flyway for migrations
- Avoid N+1 queries
- Add indexes intentionally
- Use transactions appropriately

## 추가 설명

- 모든 DB 변경은 migration 관리
- 실행계획(EXPLAIN ANALYZE) 고려

---

# Coding Style

- Prefer readability over cleverness
- Keep methods small
- Keep classes focused
- Use constructor injection
- Prefer immutable DTOs
- Use Kotlin data classes for DTOs
- Never expose entity objects directly

## 추가 설명

- 함수는 단일 책임 유지
- 비즈니스 로직은 Service 계층에 위치
- DTO는 immutable 기본 사용

---

# Exception Handling

- Use global exception handling
- Return standardized error responses
- Do not expose stack traces to clients

---

# Logging

- Use structured logging
- Add health check endpoints
- Avoid excessive logging
- Do not log sensitive information

---

# Testing

- Add unit tests for business logic
- Add integration tests for repositories
- Use Testcontainers when possible

## 추가 설명

- 핵심 비즈니스 로직 테스트 필수
- Repository integration test 작성

---

# DevOps

- Use Docker-based environments
- Prefer GitHub Actions for CI/CD
- Keep environments reproducible
- Use environment-based configuration

## 추가 설명

- dev/stage/prod 설정 분리
- .env.example 제공

---

# Documentation

- Write README documents in Korean
- Write code comments in Korean
- Keep comments concise and useful

## 추가 설명

- 불필요한 주석 금지
- 왜 필요한 코드인지 설명 중심 작성

---

# Prohibited

- Do not introduce unnecessary frameworks
- Do not overengineer
- Do not add Kubernetes prematurely
- Do not add distributed systems without clear need

## 추가 설명

- 단순한 문제는 단순하게 해결
- 초기 단계에서는 운영 복잡도 최소화

---

# Authentication

- Use OAuth2 social login
- Support Kakao login
- Support Google login
- Support Naver login
- Issue internal JWT tokens after OAuth login
- Use Access Token and Refresh Token strategy

## 추가 설명

OAuth provider token을 직접 사용하지 않는다.

소셜 로그인 완료 후,
서비스 내부 JWT를 발급한다.

기본 지원 Provider:

- Kakao
- Google
- Naver
