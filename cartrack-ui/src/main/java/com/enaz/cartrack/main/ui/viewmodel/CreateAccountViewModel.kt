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

    private var firstName: String = EMPTY_STRING
    private var lastName: String = EMPTY_STRING
    private var userName: String = EMPTY_STRING
    private var password: String = EMPTY_STRING
    private var confirmPassword: String = EMPTY_STRING

    private val createAccount = MediatorLiveData<CreateAccountViewState>()
    internal fun getLoadingLiveData(): LiveData<CreateAccountViewState> = createAccount

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun submit(
        fName: String, lName: String,
        user: String, pass: String
    ) {
        if ((password == confirmPassword)) {
            createAccount.postValue(LoadingModel(true))
            launch {
                var account = Account(fName, lName, user, pass)
                insertAccount(account)
            }
        } else {
            createAccount.postValue(MatchPasswordModel(false))
        }
    }

    private suspend fun insertAccount(account: Account) {
        withContext(Dispatchers.IO) {
            try {
                if (cartrackRepository.isUserExist(account.userName)) {
                    createAccount.postValue(FailingModel(true))
                    createAccount.postValue(AccountExistModel(R.string.account_exist))
                } else {
                    createAccount.postValue(LoadingModel(false))
                    cartrackRepository.submit(account.modelToEntityAccount())
                }
            } catch (e: Exception) {
                createAccount.postValue(FailingModel(true))
            }
        }
    }

    fun onFirstNameChanged(text: CharSequence) {
        firstName = text.toString()
        validate()
    }

    fun onLastNameChanged(text: CharSequence) {
        lastName = text.toString()
        validate()
    }

    fun onUsernameChanged(text: CharSequence) {
        userName = text.toString()
        validate()
    }

    fun onPasswordChanged(text: CharSequence) {
        password = text.toString()
        validate()
    }

    fun onConfirmPasswordChanged(text: CharSequence) {
        confirmPassword = text.toString()
        validate()
    }

    private fun validate() {
        if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() &&
            userName.length >= 6 && password.length >= 6 &&
            confirmPassword.length >= 6) {
            createAccount.postValue(ValidModel(true))
        } else {
            createAccount.postValue(ValidModel(false))
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
