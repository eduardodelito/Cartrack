package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.repository.UsersRepository
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.mapper.userResponseModelToUsersResponse
import com.enaz.cartrack.main.ui.model.ProgressLoading
import com.enaz.cartrack.main.ui.model.UsersLoading
import com.enaz.cartrack.main.ui.model.UsersViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UsersViewModel @Inject constructor(private var usersRepository: UsersRepository) :
    BaseViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val users = MediatorLiveData<UsersViewState>()
    internal fun getUsersLiveData(): LiveData<UsersViewState> = users

    /**
     * Get movie list from local data base.
     */
    fun getUsers() = usersRepository.getUsers()

    fun loadUsers() {
        users.postValue(ProgressLoading(true))
        launch {
            insertUsers()
        }
    }

    private suspend fun insertUsers() {
        withContext(Dispatchers.IO) {
            try {
                users.postValue(ProgressLoading(false))
                users.postValue(UsersLoading(false))
                usersRepository.insertUsers(usersRepository.getUsersResponse().getUsersResponse().userResponseModelToUsersResponse())
            } catch (e: Exception) {
                e.printStackTrace()
                users.postValue(ProgressLoading(false))
                users.postValue(UsersLoading(false))
            }
        }
    }

    /**
     * Refresh movie list from the lat search.
     */
    fun refresh() {
        users.postValue(UsersLoading(true))
        launch {
            insertUsers()
        }
    }
}
