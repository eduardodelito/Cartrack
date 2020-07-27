package com.enaz.cartrack.main.client.repository

import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.entity.AccountEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
interface CartrackRepository {
    fun submit(account: AccountEntity)

    fun isUserExist(userName: String): Boolean

    fun deleteAccount();

    fun getUsers()
}

class CartrackRepositoryImpl(private var cartrackApiClient: CartrackApiClient,
                             private var accountDao: AccountDao) : CartrackRepository {
    override fun submit(account: AccountEntity) {
        accountDao.insertAccount(account)
    }

    override fun isUserExist(userName: String) = accountDao.isUserExist(userName)

    override fun deleteAccount() {
        accountDao.deleteAccount()
    }

    override fun getUsers() {

    }
}
