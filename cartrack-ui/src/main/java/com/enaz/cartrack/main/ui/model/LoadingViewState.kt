package com.enaz.cartrack.main.ui.model

/**
 * Created by eduardo.delito on 7/26/20.
 */
sealed class LoadingViewState
class LoadingModel(val isLoading: Boolean) : LoadingViewState()
class FailingModel(val isFailing: Boolean) : LoadingViewState()
class AccountExistModel(val message: Int) : LoadingViewState()