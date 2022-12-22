package com.plenart.organizeme_compose.validation

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to be at least 6 characters"
            )
        }

        val containsLetter = password.any { it.isLetter() }
        val containsDigit = password.any { it.isDigit() }

        if (!(containsLetter && containsDigit)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one digit and letter"
            )
        }
        return ValidationResult(true)
    }
}
