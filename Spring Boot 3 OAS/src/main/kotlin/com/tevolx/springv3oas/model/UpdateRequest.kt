package com.tevolx.springv3oas.model

data class UpdateRequest (
    val stringField: String,
    val nullableStringField: String? = null,
    val intField: Int,
    val longField: Long,
    val boolean: Boolean,
)