package com.template.ping.infrastructure

import com.template.ping.domain.PingMessage
import org.springframework.data.jpa.repository.JpaRepository

interface PingMessageRepository : JpaRepository<PingMessage, Long>

