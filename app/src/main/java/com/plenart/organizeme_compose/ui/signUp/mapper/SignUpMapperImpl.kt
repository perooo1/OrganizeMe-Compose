package com.plenart.organizeme_compose.ui.signUp.mapper

import android.util.Log
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.signUp.SignUpViewState

class SignUpMapperImpl() : SignUpMapper {
    /*
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
*/

    override fun toSignUpViewState(
        heroMessage: String,
        name: String,
        email: String,
        password: String
    ): SignUpViewState {

        Log.i(
            "EDITTEXT",
            "MapperImpl: before SignUpViewStateConstructor: name:${name}, email: $email, password: $password  "
        )

        val vs = SignUpViewState(
            heroMessage,
            CredentialsInputCardViewState(
                name, email, password
            )
        )

        Log.i(
            "EDITTEXT",
            "MapperImpl: after SignUpViewStateConstructor: name:${name}, email: $email, password: $password  "
        )

        Log.i(
            "EDITTEXT",
            "MapperImpl: after SignUpViewStateConstructor: name:${vs.credentialsInputCardViewState.name}, email: ${vs.credentialsInputCardViewState.email}, password: ${vs.credentialsInputCardViewState.password}"
        )

        return vs
    }

}
