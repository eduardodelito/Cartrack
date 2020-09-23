package com.enaz.cartrack.main.client.repository

import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.entity.AccountEntity

/**
 * AccountRepository class for saving and querying data from db.
 *
 * Created by eduardo.delito on 7/26/20.
 */
interface AccountRepository {
    /**
     * Method for saving account.
     * @param account Account entity data.
     */
    fun submit(account: AccountEntity)

    /**
     * Method for to check account if exist.
     * @param userName
     */
    fun isUserExist(userName: String): Boolean

    /**
     * Method for validating userName and Password.
     * @param userName
     * @param password
     */
    fun isUsernamePasswordValid(userName: String, password: String): Boolean

    /**
     * Method for to delete account.
     */
    fun deleteAccount();
}

class AccountRepositoryImpl(private var accountDao: AccountDao) : AccountRepository {

    /**
     * Method for saving account.
     * @param account Account entity data.
     */
    override fun submit(account: AccountEntity) {
        accountDao.insertAccount(account)
    }

    /**
     * Method for to check account if exist.
     * @param userName
     */
    override fun isUserExist(userName: String) = accountDao.isUserExist(userName)

    /**
     * Method for validating userName and Password.
     * @param userName
     * @param password
     */
    override fun isUsernamePasswordValid(userName: String, password: String) =
        accountDao.isUsenamePasswordValid(userName, password)

    /**
     * Method for to delete account.
     */
    override fun deleteAccount() {
        accountDao.deleteAccount()
    }
}
