package com.enaz.cartrack.main.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enaz.cartrack.main.db.entity.AccountEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: AccountEntity)

    @Query("SELECT * FROM AccountEntity WHERE userName = :userName LIMIT 1")
    fun isUserExist(userName: String) : Boolean

    @Query("SELECT * FROM AccountEntity WHERE userName = :userName AND password = :password LIMIT 1")
    fun isUsenamePasswordValid(userName: String, password: String) : Boolean

    @Query("DELETE FROM AccountEntity")
    fun deleteAccount()
}
