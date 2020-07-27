package com.enaz.cartrack.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enaz.cartrack.main.db.converter.UsersConverter
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.AccountEntity
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Database(entities = [AccountEntity::class, UsersEntity::class], version = 1, exportSchema = false)
@TypeConverters(UsersConverter::class)
abstract class CartrackDB : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun usersDao(): UsersDao

    companion object {
        const val DATABASE_NAME = "CartrackDB"
    }
}
