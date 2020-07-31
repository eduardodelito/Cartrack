package com.enaz.cartrack.main.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * UsersEntity data class
 *
 * Created by eduardo.delito on 7/27/20.
 */
@Entity(tableName = "UsersEntity")
data class UsersEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "email")
    var email: String,
    @Embedded(prefix = "address")
    var address: Address,
    @ColumnInfo(name = "phone")
    var phone: String,
    @ColumnInfo(name = "website")
    var website: String,
    @Embedded(prefix = "company")
    var company: Company

)

data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    @Embedded(prefix = "geo")
    var geo: Geo): Serializable

data class Geo(
    var lat: String,
    var lng: String): Serializable

data class Company(
    var name: String,
    var catchPhrase: String,
    var bs: String): Serializable
