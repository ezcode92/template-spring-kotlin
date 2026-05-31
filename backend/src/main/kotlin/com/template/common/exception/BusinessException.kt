package com.template.common.exception

import org.springframework.http.HttpStatus

open class BusinessException(
    val errorCode: String,
    override val message: String,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
) : RuntimeException(message)

