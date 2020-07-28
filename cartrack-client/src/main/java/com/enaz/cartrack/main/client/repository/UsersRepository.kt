package com.enaz.cartrack.main.client.repository

import androidx.lifecycle.LiveData
import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.service.UsersApiService
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Created by eduardo.delito on 7/28/20.
 */
interface UsersRepository {
    fun getUsersResponse(): UsersApiService

    fun insertUsers(users: List<UsersEntity>)

    fun getUsers(): LiveData<List<UsersEntity>>
}

class UsersRepositoryImpl(private var cartrackApiClient: CartrackApiClient, private var usersDao: UsersDao): UsersRepository {

    override fun getUsersResponse() = cartrackApiClient.getUsersResponse()

    override fun insertUsers(users: List<UsersEntity>) {
        usersDao.deleteUsers()
        usersDao.insertUsers(users)
    }

    override fun getUsers() = usersDao.getUsers()
}
