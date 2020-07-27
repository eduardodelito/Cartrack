package com.enaz.cartrack.main.ui.model

/**
 * Created by eduardo.delito on 7/26/20.
 */
sealed class LoginViewState
class LoginValidModel(var isValid: Boolean) : LoginViewState()
class LoginSuccessModel(var isSuccess: Boolean) : LoginViewState()
class ErrorModel(val message: Int) : LoginViewState()