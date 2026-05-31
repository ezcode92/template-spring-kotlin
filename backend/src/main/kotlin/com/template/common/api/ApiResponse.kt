package com.template.common.api

import java.time.Instant

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val error: ErrorResponse?,
    val timestamp: Instant = Instant.now(),
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(success = true, data = data, error = null)

        fun failure(code: String, message: String, details: Map<String, String> = emptyMap()): ApiResponse<Nothing> =
            ApiResponse(success = false, data = null, error = ErrorResponse(code, message, details))
    }
}

data class ErrorResponse(
    val code: String,
    val message: String,
    val details: Map<String, String> = emptyMap(),
)

