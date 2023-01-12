package com.plenart.organizeme_compose.validation

data class ValidationResult(
    var successful: Boolean,
    val errorMessage: String? = null
)
