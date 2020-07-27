package com.enaz.cartrack.main.ui.model

/**
 * Created by eduardo.delito on 7/26/20.
 */
sealed class CreateAccountViewState
class LoadingModel(val isLoading: Boolean) : CreateAccountViewState()
class FailingModel(val isFailing: Boolean) : CreateAccountViewState()
class ValidModel(val isValid: Boolean) : CreateAccountViewState()
class MatchPasswordModel(val isMatchPassword: Boolean) : CreateAccountViewState()
class AccountExistModel(val message: Int) : CreateAccountViewState()