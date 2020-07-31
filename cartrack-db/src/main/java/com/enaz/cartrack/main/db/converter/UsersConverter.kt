package com.enaz.cartrack.main.db.converter

import androidx.room.TypeConverter
import com.enaz.cartrack.main.db.entity.Address
import com.enaz.cartrack.main.db.entity.Company
import com.enaz.cartrack.main.db.entity.Geo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Object converter class
 *
 * Created by eduardo.delito on 7/27/20.
 */
class UsersConverter {
    @TypeConverter
    fun fromAddressString(value: String?): Address? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<Address>() {}.type
        return Gson().fromJson<Address>(value, listType)
    }

    @TypeConverter
    fun fromAddresObject(address: Address?): String? {
        if (address == null) {
            return ""
        }
        val gson = Gson()
        return gson.toJson(address)
    }

    @TypeConverter
    fun fromCompanyString(value: String?): Company? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<Company>() {}.type
        return Gson().fromJson<Company>(value, listType)
    }

    @TypeConverter
    fun fromCompanyObject(company: Company?): String? {
        if (company == null) {
            return ""
        }
        val gson = Gson()
        return gson.toJson(company)
    }

    @TypeConverter
    fun fromGeoString(value: String?): Geo? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<Geo>() {}.type
        return Gson().fromJson<Geo>(value, listType)
    }

    @TypeConverter
    fun fromGeoObject(geo: Geo?): String? {
        if (geo == null) {
            return ""
        }
        val gson = Gson()
        return gson.toJson(geo)
    }
}
