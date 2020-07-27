package com.enaz.cartrack.main.ui.model

/**
 * Created by eduardo.delito on 7/27/20.
 */
sealed class UsersViewState
class UsersLoading(var isLoading: Boolean): UsersViewState()
//class ErrorLoading(var errorPair: Pair<Boolean, String>?): UsersViewState()