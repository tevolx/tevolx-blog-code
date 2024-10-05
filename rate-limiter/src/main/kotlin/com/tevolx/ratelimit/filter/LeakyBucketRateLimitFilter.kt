package com.tevolx.ratelimit.filter

import com.tevolx.ratelimit.filter.RateLimitFilter.Companion.LEAKY_BUCKET_PATH
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.server.PathContainer
import org.springframework.stereotype.Component
import org.springframework.web.util.pattern.PathPatternParser

@Component
class LeakyBucketRateLimitFilter : RateLimitFilter, Filter {

    override fun doFilter(
        request: ServletRequest?,
        response: ServletResponse?,
        chain: FilterChain?
    ) {
        val path = (request as HttpServletRequest).servletPath ?: StringUtils.EMPTY
        val isMatch = PathPatternParser().parse(path).matches(PathContainer.parsePath(LEAKY_BUCKET_PATH))

        if (isMatch && !isAllowed()) {
            (response as HttpServletResponse).apply {
                status = HttpStatus.TOO_MANY_REQUESTS.value()
            }
        } else {
            chain?.doFilter(request, response)
        }
    }

    override fun isAllowed(): Boolean {
        return true
    }
}