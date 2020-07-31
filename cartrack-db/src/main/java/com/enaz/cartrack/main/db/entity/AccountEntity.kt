package com.enaz.cartrack.main.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AccountEntity data class
 *
 * Created by eduardo.delito on 7/26/20.
 */
@Entity(tableName = "AccountEntity")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "password") var password: String
)
