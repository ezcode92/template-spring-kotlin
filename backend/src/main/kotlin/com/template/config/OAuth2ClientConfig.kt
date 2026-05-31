package com.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod

@Configuration
class OAuth2ClientConfig(
    private val properties: OAuth2ProviderProperties,
) {

    @Bean
    @Conditional(OAuth2ConfiguredCondition::class)
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        val registrations = listOfNotNull(
            googleRegistration(),
            kakaoRegistration(),
            naverRegistration(),
        )

        return InMemoryClientRegistrationRepository(registrations)
    }

    private fun googleRegistration(): ClientRegistration? {
        if (!properties.google.isConfigured()) {
            return null
        }

        return CommonOAuth2Provider.GOOGLE
            .getBuilder("google")
            .clientId(properties.google.clientId)
            .clientSecret(properties.google.clientSecret)
            .scope("email", "profile")
            .build()
    }

    private fun kakaoRegistration(): ClientRegistration? {
        if (!properties.kakao.isConfigured()) {
            return null
        }

        return ClientRegistration.withRegistrationId("kakao")
            .clientId(properties.kakao.clientId)
            .clientSecret(properties.kakao.clientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("profile_nickname", "account_email")
            .authorizationUri("https://kauth.kakao.com/oauth/authorize")
            .tokenUri("https://kauth.kakao.com/oauth/token")
            .userInfoUri("https://kapi.kakao.com/v2/user/me")
            .userNameAttributeName("id")
            .clientName("Kakao")
            .build()
    }

    private fun naverRegistration(): ClientRegistration? {
        if (!properties.naver.isConfigured()) {
            return null
        }

        return ClientRegistration.withRegistrationId("naver")
            .clientId(properties.naver.clientId)
            .clientSecret(properties.naver.clientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("name", "email")
            .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
            .tokenUri("https://nid.naver.com/oauth2.0/token")
            .userInfoUri("https://openapi.naver.com/v1/nid/me")
            .userNameAttributeName("response")
            .clientName("Naver")
            .build()
    }
}

class OAuth2ConfiguredCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val environment = context.environment

        return listOf("google", "kakao", "naver").any { provider ->
            val clientId = environment.getProperty("oauth2.$provider.client-id").orEmpty()
            val clientSecret = environment.getProperty("oauth2.$provider.client-secret").orEmpty()
            clientId.isNotBlank() && clientSecret.isNotBlank()
        }
    }
}
