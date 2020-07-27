package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.UsersResponse
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.model.AvailableModel
import com.enaz.cartrack.main.ui.model.DetailsViewState

class DetailsViewModel : BaseViewModel() {
    private val details = MediatorLiveData<DetailsViewState>()
    internal fun getDetailsLiveData(): LiveData<DetailsViewState> = details

    var user: UsersResponse? = null

    fun details(userItem: UsersResponse?) {
        if (userItem != null)
            details.postValue(AvailableModel(true))
    }
}
