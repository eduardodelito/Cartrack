package com.enaz.cartrack.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.common.viewmodel.BaseViewModel
import com.enaz.cartrack.main.ui.mapper.userResponseModelToUsersResponse
import com.enaz.cartrack.main.ui.model.UsersLoading
import com.enaz.cartrack.main.ui.model.UsersViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UsersViewModel @Inject constructor(private var cartrackRepository: CartrackRepository) :
    BaseViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val users = MediatorLiveData<UsersViewState>()
    internal fun getUsersLiveData(): LiveData<UsersViewState> = users

    /**
     * Get movie list from local data base.
     */
    fun getUsers() = cartrackRepository.getUsers()

    fun loadUsers() {
        users.postValue(UsersLoading(true))
        launch {
            insertUsers()
        }
    }

    private suspend fun insertUsers() {
        withContext(Dispatchers.IO) {
            try {
                users.postValue(UsersLoading(false))
                cartrackRepository.insertUsers(cartrackRepository.getUsersResponse().getUsersResponse().userResponseModelToUsersResponse())
            } catch (e: Exception) {
                e.printStackTrace()
                users.postValue(UsersLoading(false))
            }
        }
    }

    /**
     * Search movies based on the query string value.
     * @param query string to search.
     */
    fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            //search Users
        }
        return false
    }

    /**
     * Refresh movie list from the lat search.
     */
    fun refresh() {
        loadUsers()
    }

}