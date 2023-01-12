package com.plenart.organizeme_compose.validation

interface Validator {
    fun execute(input: String): ValidationResult
}
