package com.enaz.cartrack.main.ui.model

/**
 * UsersViewState data model class.
 *
 * Created by eduardo.delito on 7/27/20.
 */
sealed class UsersViewState
class UsersLoading(var isLoading: Boolean): UsersViewState()
class ProgressLoading(var isLoading: Boolean): UsersViewState()