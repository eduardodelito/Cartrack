package com.enaz.cartrack.main.di

import android.app.Application
import androidx.room.Room
import com.enaz.cartrack.main.db.CartrackDB
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.CountriesDao
import com.enaz.cartrack.main.db.dao.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
class DBModule(private var application: Application) {
    private var cartrackDB: CartrackDB

    init {
        synchronized(this) {
            val instance = Room.databaseBuilder(
                application,
                CartrackDB::class.java,
                CartrackDB.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
            cartrackDB = instance
            instance
        }
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): CartrackDB {
        return cartrackDB
    }

    @Singleton
    @Provides
    fun providesAccountDao(cartrackDB: CartrackDB): AccountDao {
        return cartrackDB.accountDao()
    }

    @Singleton
    @Provides
    fun providesUsersDao(cartrackDB: CartrackDB): UsersDao {
        return cartrackDB.usersDao()
    }

    @Singleton
    @Provides
    fun providesCountriesDao(cartrackDB: CartrackDB): CountriesDao {
        return cartrackDB.countriesDao()
    }
}
