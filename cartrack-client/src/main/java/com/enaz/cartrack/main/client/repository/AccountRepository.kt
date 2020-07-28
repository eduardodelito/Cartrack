package com.enaz.cartrack.main.client.repository

import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.AccountEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
interface AccountRepository {
    fun submit(account: AccountEntity)

    fun isUserExist(userName: String): Boolean

    fun isUsernamePasswordValid(userName: String, password: String): Boolean

    fun deleteAccount();
}

class AccountRepositoryImpl(private var accountDao: AccountDao) : AccountRepository {
    override fun submit(account: AccountEntity) {
        accountDao.insertAccount(account)
    }

    override fun isUserExist(userName: String) = accountDao.isUserExist(userName)

    override fun isUsernamePasswordValid(userName: String, password: String) =
        accountDao.isUsenamePasswordValid(userName, password)

    override fun deleteAccount() {
        accountDao.deleteAccount()
    }
}
