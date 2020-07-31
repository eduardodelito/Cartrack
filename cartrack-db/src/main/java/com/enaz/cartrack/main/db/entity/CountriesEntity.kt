package com.enaz.cartrack.main.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * CountriesEntity data class
 *
 * Created by eduardo.delito on 7/28/20.
 */
@Entity(tableName = "CountriesEntity")
data class CountriesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "alpha2Code")
    var alpha2Code: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "flag")
    var flag: String?
)
