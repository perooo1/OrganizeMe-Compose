package com.plenart.organizeme_compose.ui.signUp.mapper

import com.plenart.organizeme_compose.ui.signUp.SignUpViewState

interface SignUpMapper {
    fun toSignUpViewState(
        heroMessage: String,
        name: String = "",
        email: String,
        password: String
    ): SignUpViewState
}
