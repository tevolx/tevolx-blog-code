package com.tevolx.ratelimit.controller

import com.tevolx.ratelimit.model.RateLimitResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/rate-limit")
class RateLimitController{

    @GetMapping("/leaky-bucket")
    fun leakyBucket(): ResponseEntity<RateLimitResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(RateLimitResponse(HttpStatus.OK.value(), HttpStatus.OK.reasonPhrase))
    }
}