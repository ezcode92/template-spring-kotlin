package com.template.ping.presentation

import com.template.common.api.ApiResponse
import com.template.ping.application.PingService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController(
    private val pingService: PingService,
) {

    @PostMapping("/api/v1/ping")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: PingCreateRequest): ApiResponse<PingCreateResponse> {
        val result = pingService.create(request.message)
        return ApiResponse.success(PingCreateResponse(id = result.id, message = result.message))
    }
}

data class PingCreateRequest(
    @field:NotBlank(message = "메시지는 필수입니다.")
    @field:Size(max = 100, message = "메시지는 100자를 초과할 수 없습니다.")
    val message: String,
)

data class PingCreateResponse(
    val id: Long,
    val message: String,
)

