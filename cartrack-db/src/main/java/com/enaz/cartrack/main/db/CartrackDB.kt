package com.enaz.cartrack.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.entity.AccountEntity

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Database(entities = [AccountEntity::class], version = 1, exportSchema = false)
abstract class CartrackDB : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        const val DATABASE_NAME = "CartrackDB"
    }
}
