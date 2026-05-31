package com.template.auth.application

import com.template.config.JwtProperties
import org.springframework.stereotype.Service

@Service
class JwtTokenService(
    private val jwtProperties: JwtProperties,
) {

    fun resolveSubject(token: String): String? {
        // TODO: 서명 검증과 만료 검증을 구현한 뒤 subject를 반환한다.
        if (token.isBlank() || jwtProperties.secret.isBlank()) {
            return null
        }

        return null
    }

    fun issueTokenPair(subject: String): TokenPair {
        // TODO: OAuth 로그인 성공 후 내부 access/refresh token을 발급한다.
        require(subject.isNotBlank()) { "subject는 비어 있을 수 없습니다." }
        throw UnsupportedOperationException("JWT 발급 구현이 필요합니다.")
    }
}

data class TokenPair(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String = "Bearer",
    val expiresIn: Long,
)

