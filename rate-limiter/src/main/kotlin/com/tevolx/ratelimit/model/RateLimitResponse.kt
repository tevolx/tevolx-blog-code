package com.tevolx.ratelimit.model

import org.springframework.http.HttpStatus

data class RateLimitResponse(
    val status: HttpStatus,
    val message: String,
)