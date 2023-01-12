package com.plenart.organizeme_compose.validation

private const val MIN_PASSWORD_LENGTH = 6


class ValidatePassword : Validator {

    override fun execute(input: String): ValidationResult {
        val length = checkLength(input)
        val digit = checkDigit(input)
        val uppercase = checkUppercase(input)

        return if (!length.successful) {
            length
        } else if (!digit.successful) {
            digit
        } else if (!uppercase.successful) {
            uppercase
        } else {
            ValidationResult(true)
        }
    }

    private fun checkLength(password: String): ValidationResult {
        return if (password.length < MIN_PASSWORD_LENGTH) {
            ValidationResult(
                successful = false,
                errorMessage = "Password needs to be at least 6 characters"
            )
        } else {
            ValidationResult(true)
        }
    }

    private fun checkUppercase(password: String): ValidationResult {
        val uppercase = password.any { it.isUpperCase() }

        return if (!uppercase) {
            ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one uppercase letter"
            )
        } else {
            ValidationResult(true)
        }
    }

    private fun checkDigit(password: String): ValidationResult {
        val digit = password.any { it.isDigit() }

        return if (!digit) {
            ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one digit"
            )
        } else {
            ValidationResult(true)
        }
    }
}
