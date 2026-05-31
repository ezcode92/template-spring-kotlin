package com.template.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "oauth2")
data class OAuth2ProviderProperties(
    val google: OAuth2ClientProperty = OAuth2ClientProperty(),
    val kakao: OAuth2ClientProperty = OAuth2ClientProperty(),
    val naver: OAuth2ClientProperty = OAuth2ClientProperty(),
)

data class OAuth2ClientProperty(
    val clientId: String = "",
    val clientSecret: String = "",
) {
    fun isConfigured(): Boolean = clientId.isNotBlank() && clientSecret.isNotBlank()
}

