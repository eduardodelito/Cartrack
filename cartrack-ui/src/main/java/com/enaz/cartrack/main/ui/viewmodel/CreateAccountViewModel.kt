package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.repository.AccountRepository
import com.enaz.cartrack.main.client.repository.CountriesRepository
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.fragment.R
import com.enaz.cartrack.main.ui.mapper.countriesModelToCountriesEntity
import com.enaz.cartrack.main.ui.mapper.modelToEntityAccount
import com.enaz.cartrack.main.ui.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Create Account View model class.
 *
 * Created by eduardo.delito on 7/27/20.
 */
class CreateAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val countriesRepository: CountriesRepository
) :
    BaseViewModel(), CoroutineScope {

    private var firstName: String = EMPTY_STRING
    private var lastName: String = EMPTY_STRING
    private var userName: String = EMPTY_STRING
    private var password: String = EMPTY_STRING
    private var confirmPassword: String = EMPTY_STRING
    private var country: String = EMPTY_STRING

    private val createAccount = MediatorLiveData<CreateAccountViewState>()
    internal fun getLoadingLiveData(): LiveData<CreateAccountViewState> = createAccount

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    /**
     * Method to submit Account to insert to database.
     * @param fName
     * @param lName
     * @param user
     * @param pass
     */
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

    /**
     * Suspend method to inser account data into database.
     * @param account data.
     */
    private suspend fun insertAccount(account: Account) {
        withContext(Dispatchers.IO) {
            try {
                if (accountRepository.isUserExist(account.userName)) {
                    createAccount.postValue(AccountExistModel(R.string.account_exist))
                } else {
                    createAccount.postValue(AccountExistModel(null))
                    createAccount.postValue(LoadingModel(false))
                    accountRepository.submit(account.modelToEntityAccount())
                }
            } catch (e: Exception) {
                createAccount.postValue(AccountExistModel(R.string.account_error))
                createAccount.postValue(FailingModel(true))
            }
        }
    }

    /**
     *  Method to load countries.
     */
    fun loadCountries() {
        createAccount.postValue(CountryLoadingModel(true))
        launch {
            insertCountries()
        }
    }

    /**
     * Suspend method to insert loaded countries from service.
     */
    private suspend fun insertCountries() {
        withContext(Dispatchers.IO) {
            try {
                val list = countriesRepository.getCountriesResponse().getCountries()
                countriesRepository.insertCountries(list.countriesModelToCountriesEntity())
                createAccount.postValue(CountryLoadingModel(false))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Load livedata countries from database.
     */
    fun getCountries() = countriesRepository.getCountries()

    /**
     * Delete countries from database.
     */
    fun deleteCountries() {
        launch {
            withContext(Dispatchers.IO) {
                try {
                    countriesRepository.deleteCountries()
                } catch (e: Exception) {
                   e.printStackTrace()
                }
            }
        }
    }

    /**
     * Textwatcher method for firstname.
     * @param text
     */
    fun onFirstNameChanged(text: CharSequence) {
        firstName = text.toString()
        validate()
    }

    /**
     * Textwatcher method for lastname.
     * @param text
     */
    fun onLastNameChanged(text: CharSequence) {
        lastName = text.toString()
        validate()
    }

    /**
     * Textwatcher method for username.
     * @param text
     */
    fun onUsernameChanged(text: CharSequence) {
        userName = text.toString()
        validate()
    }

    /**
     * Textwatcher method for password.
     * @param text
     */
    fun onPasswordChanged(text: CharSequence) {
        password = text.toString()
        validate()
    }

    /**
     * Textwatcher method for confirm password.
     * @param text
     */
    fun onConfirmPasswordChanged(text: CharSequence) {
        confirmPassword = text.toString()
        validate()
    }

    /**
     * Textwatcher method for country.
     * @param text
     */
    fun onCountryChanged(text: CharSequence) {
        country = text.toString()
        validate()
    }

    /**
     * Method to validate all text in the fields.
     * @param text
     */
    private fun validate() {
        if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !country.isNullOrEmpty() &&
            userName.length >= 6 && password.length >= 6 &&
            confirmPassword.length >= 6
        ) {
            createAccount.postValue(ValidModel(true))
        } else {
            createAccount.postValue(ValidModel(false))
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
