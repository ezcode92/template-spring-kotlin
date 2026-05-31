package com.template.common.exception

import com.template.common.api.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(exception.status)
            .body(ApiResponse.failure(exception.errorCode, exception.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val details = exception.bindingResult.fieldErrors.associate { error: FieldError ->
            error.field to (error.defaultMessage ?: "잘못된 요청 값입니다.")
        }

        return ResponseEntity
            .badRequest()
            .body(ApiResponse.failure("VALIDATION_FAILED", "요청 값 검증에 실패했습니다.", details))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(exception: ConstraintViolationException): ResponseEntity<ApiResponse<Nothing>> {
        val details = exception.constraintViolations.associate { violation ->
            violation.propertyPath.toString() to violation.message
        }

        return ResponseEntity
            .badRequest()
            .body(ApiResponse.failure("VALIDATION_FAILED", "요청 값 검증에 실패했습니다.", details))
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.failure("UNAUTHORIZED", "인증이 필요합니다."))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.failure("FORBIDDEN", "접근 권한이 없습니다."))
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(
        exception: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ApiResponse<Nothing>> {
        // 내부 오류 원인은 로그에만 남기고 클라이언트에는 노출하지 않는다.
        logger.error(exception) { "Unhandled exception path=${request.requestURI}" }

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.failure("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."))
    }
}

