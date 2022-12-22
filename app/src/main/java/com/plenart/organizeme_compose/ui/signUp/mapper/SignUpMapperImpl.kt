package com.plenart.organizeme_compose.ui.signUp.mapper

import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.signUp.SignUpViewState

class SignUpMapperImpl() : SignUpMapper {
    override fun toSignUpViewState(
        heroMessage: String,
        name: String,
        email: String,
        password: String
    ): SignUpViewState =
        SignUpViewState(
            heroMessage,
            CredentialsInputCardViewState(
                name, email, password
            )
        )

}
