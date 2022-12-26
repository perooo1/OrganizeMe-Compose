package com.plenart.organizeme_compose.validation

import android.util.Patterns

class ValidateEmail : Validator {   //strings should come from string values file

    override fun execute(input: String): ValidationResult {
        val blank = checkIfBlank(input)
        val pattern = checkPattern(input)

        return if(!blank.successful){
            blank
        } else if(!pattern.successful){
            pattern
        } else{
            ValidationResult(true)
        }
    }

    private fun checkIfBlank(email: String): ValidationResult {
        return if (email.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = "Email can't be empty"
            )
        } else {
            ValidationResult(true)
        }
    }

    private fun checkPattern(email: String): ValidationResult {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(
                successful = false,
                errorMessage = "Invalid email"
            )
        } else {
            ValidationResult(true)
        }
    }
}
