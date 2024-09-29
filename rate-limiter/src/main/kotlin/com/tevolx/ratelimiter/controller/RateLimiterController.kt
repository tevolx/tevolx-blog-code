package com.tevolx.ratelimiter.controller

import com.tevolx.ratelimiter.service.RateLimiterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/v1")
class RateLimiterController(
    private val rateLimiterService: RateLimiterService
) {

    @GetMapping("/rate-limit/leaky-bucket")
    fun leakyBucket(): ResponseEntity<Unit> {
        rateLimiterService.leakyBucket()
        return ResponseEntity.ok().build()
    }
}