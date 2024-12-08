package com.tevolx.ratelimit.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

object JsonUtils {

    private val objectMapper = ObjectMapper()
        .registerModules(KotlinModule.Builder().build())

    fun toJson(obj: Any): String? = try {
        objectMapper.writeValueAsString(obj)
    } catch (e: Exception) {
        null
    }
}