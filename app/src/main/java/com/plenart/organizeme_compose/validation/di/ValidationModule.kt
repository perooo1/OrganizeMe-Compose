package com.plenart.organizeme_compose.validation.di

import com.plenart.organizeme_compose.validation.ValidateEmail
import com.plenart.organizeme_compose.validation.ValidateName
import com.plenart.organizeme_compose.validation.ValidatePassword
import com.plenart.organizeme_compose.validation.Validator
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val VALIDATOR_NAME_QUALIFIER = "name"
const val VALIDATOR_EMAIL_QUALIFIER = "email"
const val VALIDATOR_PASSWORD_QUALIFIER = "password"

val validationModule = module {
    single<Validator>(named(VALIDATOR_NAME_QUALIFIER)) { ValidateName() }
    single<Validator>(named(VALIDATOR_EMAIL_QUALIFIER)) { ValidateEmail() }
    single<Validator>(named(VALIDATOR_PASSWORD_QUALIFIER)) { ValidatePassword() }
}
