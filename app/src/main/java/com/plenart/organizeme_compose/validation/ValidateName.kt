package com.plenart.organizeme_compose.validation

private const val MIN_NAME_LENGTH = 3

class ValidateName : Validator {

    override fun execute(input: String): ValidationResult {
        val length = checkLength(input)
        return if (!length.successful) {
            length
        } else {
            ValidationResult(true)
        }
    }

    private fun checkLength(name: String): ValidationResult {
        return if (name.length < MIN_NAME_LENGTH) {
            ValidationResult(
                successful = false,
                errorMessage = "Name must be at least three characters long"
            )
        } else {
            ValidationResult(true)
        }
    }
}
