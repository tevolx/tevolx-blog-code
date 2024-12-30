package com.tevolx.springv2oas.model

data class UpdateRequest (
    val stringField: String,
    val nullableStringField: String? = null,
    val intField: Int,
    val longField: Long,
    val boolean: Boolean,
)