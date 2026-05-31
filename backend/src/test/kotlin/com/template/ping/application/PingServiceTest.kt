package com.template.ping.application

import com.template.ping.domain.PingMessage
import com.template.ping.infrastructure.PingMessageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PingServiceTest {

    private val pingMessageRepository = mock<PingMessageRepository>()

    @Test
    fun `메시지를 저장하고 결과를 반환한다`() {
        whenever(pingMessageRepository.save(any<PingMessage>()))
            .thenReturn(PingMessage(id = 1L, message = "pong"))

        val service = PingService(pingMessageRepository)

        val result = service.create("pong")

        assertThat(result.id).isEqualTo(1L)
        assertThat(result.message).isEqualTo("pong")
    }
}
