package com.plenart.organizeme_compose.validation

class ValidateName {
    fun execute(name: String): ValidationResult {
        if (name.length < 3) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name must be at least three characters long"
            )
        }
        return ValidationResult(true)
    }
}
