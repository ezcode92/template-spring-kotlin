package com.template.ping.application

import com.template.ping.domain.PingMessage
import com.template.ping.infrastructure.PingMessageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PingService(
    private val pingMessageRepository: PingMessageRepository,
) {

    @Transactional
    fun create(message: String): PingResult {
        val saved = pingMessageRepository.save(PingMessage(message = message))
        return PingResult(id = saved.id, message = saved.message)
    }
}

data class PingResult(
    val id: Long,
    val message: String,
)

