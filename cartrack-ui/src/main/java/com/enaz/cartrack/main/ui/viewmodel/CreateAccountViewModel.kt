package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.fragment.R
import com.enaz.cartrack.main.ui.mapper.modelToEntityAccount
import com.enaz.cartrack.main.ui.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CreateAccountViewModel @Inject constructor(private val cartrackRepository: CartrackRepository) :
    BaseViewModel(), CoroutineScope {

    private val loading = MediatorLiveData<LoadingViewState>()
    internal fun getLoadingLiveData(): LiveData<LoadingViewState> = loading

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun submit(firstName: String, lastName: String,
               userName: String, password: String) {
        loading.postValue(LoadingModel(true))
        launch {
            var account = Account(firstName, lastName, userName, password)
            insertAccount(account)
        }


    }

    private suspend fun insertAccount(account: Account) {
        withContext(Dispatchers.IO) {
            try {
                if (cartrackRepository.isUserExist(account.userName)) {
                    loading.postValue(FailingModel(true))
                    loading.postValue(AccountExistModel(R.string.account_exist))
                } else {
                    loading.postValue(LoadingModel(false))
                    cartrackRepository.submit(account.modelToEntityAccount())
                }
            } catch (e: Exception) {
                loading.postValue(FailingModel(true))
            }
        }
    }

}
