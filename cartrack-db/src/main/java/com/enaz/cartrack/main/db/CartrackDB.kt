package com.enaz.cartrack.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enaz.cartrack.main.db.converter.UsersConverter
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.CountriesDao
import com.enaz.cartrack.main.db.dao.UsersDao
import com.enaz.cartrack.main.db.entity.AccountEntity
import com.enaz.cartrack.main.db.entity.CountriesEntity
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Database class
 *
 * Created by eduardo.delito on 7/26/20.
 */
@Database(
    entities = [AccountEntity::class, UsersEntity::class, CountriesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UsersConverter::class)
abstract class CartrackDB : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun usersDao(): UsersDao
    abstract fun countriesDao(): CountriesDao

    companion object {
        const val DATABASE_NAME = "CartrackDB"
    }
}
