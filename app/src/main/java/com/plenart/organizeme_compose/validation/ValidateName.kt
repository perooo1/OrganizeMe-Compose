package com.plenart.organizeme_compose.validation

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
        return if (name.length < 3) {
            ValidationResult(
                successful = false,
                errorMessage = "Name must be at least three characters long"
            )
        } else {
            ValidationResult(true)
        }
    }
}
