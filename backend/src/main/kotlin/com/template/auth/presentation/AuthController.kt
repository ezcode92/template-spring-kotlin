package com.template.auth.presentation

import com.template.common.api.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @GetMapping("/api/v1/auth/status")
    @ResponseStatus(HttpStatus.OK)
    fun status(): ApiResponse<AuthStatusResponse> {
        return ApiResponse.success(AuthStatusResponse(jwtEnabled = false, oauthEnabled = true))
    }
}

data class AuthStatusResponse(
    val jwtEnabled: Boolean,
    val oauthEnabled: Boolean,
)

