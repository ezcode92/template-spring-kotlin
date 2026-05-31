package com.template.health

import com.template.common.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/api/v1/health")
    fun health(): ApiResponse<HealthResponse> {
        return ApiResponse.success(HealthResponse(status = "UP"))
    }
}

data class HealthResponse(
    val status: String,
)

