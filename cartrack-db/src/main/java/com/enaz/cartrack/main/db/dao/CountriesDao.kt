package com.enaz.cartrack.main.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enaz.cartrack.main.db.entity.CountriesEntity

/**
 * Created by eduardo.delito on 7/28/20.
 */
@Dao
interface CountriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<CountriesEntity>)

    @Query("SELECT * from CountriesEntity ORDER BY id ASC")
    fun getCountries() : LiveData<List<CountriesEntity>>

    @Query("DELETE FROM CountriesEntity")
    fun deleteCountries()
}
