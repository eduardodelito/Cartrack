package com.enaz.cartrack.main.ui.model

/**
 * Created by eduardo.delito on 7/28/20.
 */
sealed class DetailsViewState
class AvailableModel(var isAvailable: Boolean) : DetailsViewState()