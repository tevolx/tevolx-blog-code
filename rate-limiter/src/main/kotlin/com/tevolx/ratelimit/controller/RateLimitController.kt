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
        Thread.sleep(500)
        return ResponseEntity
            .ok()
            .body(RateLimitResponse(HttpStatus.OK,HttpStatus.OK.reasonPhrase))
    }
}