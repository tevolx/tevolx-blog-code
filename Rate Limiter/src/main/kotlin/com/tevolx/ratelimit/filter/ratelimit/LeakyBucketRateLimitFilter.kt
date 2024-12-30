package com.tevolx.ratelimit.filter.ratelimit

import com.tevolx.ratelimit.filter.RateLimitFilter
import com.tevolx.ratelimit.filter.RateLimitFilter.Companion.LEAKY_BUCKET_PATH
import com.tevolx.ratelimit.model.RateLimitResponse
import com.tevolx.ratelimit.util.JsonUtils
import com.tevolx.ratelimit.util.Log
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.PathContainer
import org.springframework.stereotype.Component
import org.springframework.web.util.pattern.PathPatternParser
import java.util.concurrent.atomic.AtomicLong

@Component
class LeakyBucketRateLimitFilter : RateLimitFilter, Filter {
    private var lastUpdatedTime = AtomicLong(System.currentTimeMillis()) // 마지막 업데이트 시간
    private var used = AtomicLong(0) // 최근 버킷에 담긴 양

    companion object : Log {
        const val BUCKET_CAPACITY = 2L // 고정 용량의 버킷
        const val LEAK_RATE_PER_SECOND = 1L // 초당 요청이 처리되는 속도
    }

    override fun doFilter(
        request: ServletRequest?,
        response: ServletResponse?,
        chain: FilterChain?
    ) {
        val path = (request as HttpServletRequest).servletPath ?: StringUtils.EMPTY
        val isMatch = PathPatternParser().parse(path).matches(PathContainer.parsePath(LEAKY_BUCKET_PATH))

        try {
            if (isMatch && !isAllowed())
                throw RuntimeException("Too Many Request")

            used.getAndIncrement()
            chain?.doFilter(request, response)
        } catch (e: Exception) {
            (response as HttpServletResponse).apply {
                status = HttpStatus.TOO_MANY_REQUESTS.value()
                setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                writer.write(
                    JsonUtils.toJson(
                        RateLimitResponse(
                            HttpStatus.TOO_MANY_REQUESTS.value(),
                            HttpStatus.TOO_MANY_REQUESTS.reasonPhrase
                        )
                    ) ?: StringUtils.EMPTY
                )
            }
        }
    }

    override fun isAllowed(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = lastUpdatedTime.get()
        if (currentTime > lastUpdated) {
            val timePassedAfterLastUpdate = currentTime - lastUpdated // 마지막 업데이트로부터 지난 시간
            val totalLeakAfterLastUpdate =
                (LEAK_RATE_PER_SECOND * timePassedAfterLastUpdate) / 1000 // 마지막 업데이트로부터 처리된 양
            if (totalLeakAfterLastUpdate > 0) {
                if (used.get() <= totalLeakAfterLastUpdate) {
                    used.set(0)
                } else {
                    used.getAndSet(used.get() - totalLeakAfterLastUpdate)
                }
                lastUpdatedTime.set(currentTime)
            }
        }
        return used.get() < BUCKET_CAPACITY
    }
}