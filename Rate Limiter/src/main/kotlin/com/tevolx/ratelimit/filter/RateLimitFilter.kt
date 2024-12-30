package com.tevolx.ratelimit.filter

interface RateLimitFilter {

    fun isAllowed(): Boolean

    companion object {
        const val LEAKY_BUCKET_PATH = "/v1/rate-limit/leaky-bucket"
        const val TOKEN_BUCKET_PATH = "/v1/rate-limit/token-bucket"
        const val FIXED_WINDOW_PATH = "/v1/rate-limit/fixed-window"
        const val SLIDING_WINDOW_LOG_PATH = "/v1/rate-limit/sliding-window-log"
        const val SLIDING_WINDOW_COUNTER_PATH = "/v1/rate-limit/sliding-window-counter"
    }
}