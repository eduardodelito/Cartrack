package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.fragment.R
import com.enaz.cartrack.main.ui.model.ErrorModel
import com.enaz.cartrack.main.ui.model.LoginSuccessModel
import com.enaz.cartrack.main.ui.model.LoginValidModel
import com.enaz.cartrack.main.ui.model.LoginViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginViewModel @Inject constructor(private var cartrackRepository: CartrackRepository) :
    BaseViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val login = MediatorLiveData<LoginViewState>()
    internal fun getLoginLiveData(): LiveData<LoginViewState> = login
    private var userName: String = ""
    private var password: String = ""

    fun onUsernameTextChanged(text: CharSequence) {
        userName = text.toString()
        validate()
    }

    fun onPasswordTextChanged(text: CharSequence) {
        password = text.toString()
        validate()
    }

    private fun validate() {
        if (userName.length < 6 || password.length < 6) {
            login.postValue(LoginValidModel(false))
        } else {
            login.postValue(LoginValidModel(true))
        }
    }

    fun onLogin(userName: String, password: String) {
        launch {
            login(userName, password)
        }
    }

    private suspend fun login(userName: String, password: String) {
        withContext(Dispatchers.IO) {
            try {
                if (cartrackRepository.isUsernamePasswordValid(userName, password)) {
                    login.postValue(LoginSuccessModel(true))
                } else {
                    login.postValue(ErrorModel(R.string.invalid))
                }
            } catch (e: Exception) {
                login.postValue(ErrorModel(R.string.invalid))
                login.postValue(LoginSuccessModel(false))
            }
        }
    }
}
