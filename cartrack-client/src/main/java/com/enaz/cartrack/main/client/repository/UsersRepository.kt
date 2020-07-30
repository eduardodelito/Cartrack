package com.enaz.cartrack.main.client.repository

import androidx.lifecycle.LiveData
import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.service.UsersApiService
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * UsersRepository class for saving and querying data from db.
 *
 * Created by eduardo.delito on 7/28/20.
 */
interface UsersRepository {

    /**
     * Method for users api service.
     */
    fun getUsersResponse(): UsersApiService

    /**
     * Method to insert list of users.
     * @param users
     */
    fun insertUsers(users: List<UsersEntity>)

    /**
     * Method for the live data list of users.
     */
    fun getUsers(): LiveData<List<UsersEntity>>
}

class UsersRepositoryImpl(private var cartrackApiClient: CartrackApiClient, private var usersDao: UsersDao): UsersRepository {

    /**
     * Method for users api service.
     */
    override fun getUsersResponse() = cartrackApiClient.getUsersResponse()

    /**
     * Method to delete and insert list of users.
     * @param users
     */
    override fun insertUsers(users: List<UsersEntity>) {
        usersDao.deleteUsers()
        usersDao.insertUsers(users)
    }

    /**
     * Method for the live data list of users.
     */
    override fun getUsers() = usersDao.getUsers()
}
