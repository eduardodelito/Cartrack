package com.enaz.cartrack.main.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Users calls for insert/query data into the database.
 *
 * Created by eduardo.delito on 7/27/20.
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UsersEntity>)

    @Query("SELECT * from UsersEntity ORDER BY id ASC")
    fun getUsers() : LiveData<List<UsersEntity>>

    @Query("DELETE FROM UsersEntity")
    fun deleteUsers()
}