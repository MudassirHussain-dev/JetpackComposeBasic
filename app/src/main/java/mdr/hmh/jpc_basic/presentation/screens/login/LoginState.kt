package mdr.hmh.jpc_basic.presentation.screens.login

import androidx.annotation.StringRes

data class LoginState(
    val email: String="",
    val password: String="",
    val onSuccessLogin: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)
