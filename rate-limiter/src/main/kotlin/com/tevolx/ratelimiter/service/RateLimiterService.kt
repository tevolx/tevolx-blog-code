package com.tevolx.ratelimiter.service

import org.springframework.stereotype.Service

@Service
class RateLimiterService {

    fun leakyBucket() {
        Thread.sleep(500)
    }
}