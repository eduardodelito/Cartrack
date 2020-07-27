package com.enaz.cartrack.main.client.repository

import androidx.lifecycle.LiveData
import com.enaz.cartrack.main.CartrackApiService
import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.AccountEntity
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
interface CartrackRepository {
    fun submit(account: AccountEntity)

    fun isUserExist(userName: String): Boolean

    fun isUsernamePasswordValid(userName: String, password: String): Boolean

    fun deleteAccount();

    fun getUsersResponse(): CartrackApiService

    fun insertUsers(users: List<UsersEntity>)

    fun getUsers(): LiveData<List<UsersEntity>>
}

class CartrackRepositoryImpl(
    private var cartrackApiClient: CartrackApiClient,
    private var accountDao: AccountDao,
    private var usersDao: UsersDao
) : CartrackRepository {
    override fun submit(account: AccountEntity) {
        accountDao.insertAccount(account)
    }

    override fun isUserExist(userName: String) = accountDao.isUserExist(userName)

    override fun isUsernamePasswordValid(userName: String, password: String) =
        accountDao.isUsenamePasswordValid(userName, password)

    override fun deleteAccount() {
        accountDao.deleteAccount()
    }

    override fun getUsersResponse() = cartrackApiClient.getCartrackResponse()

    override fun insertUsers(users: List<UsersEntity>) {
        usersDao.deleteUsers()
        usersDao.insertUsers(users)
    }

    override fun getUsers() = usersDao.getUsers()
}
