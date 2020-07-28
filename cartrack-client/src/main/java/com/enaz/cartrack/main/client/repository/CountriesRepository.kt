package com.enaz.cartrack.main.client.repository

import androidx.lifecycle.LiveData
import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.service.CountriesApiService
import com.enaz.cartrack.main.db.dao.CountriesDao
import com.enaz.cartrack.main.db.entity.CountriesEntity
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Created by eduardo.delito on 7/28/20.
 */
interface CountriesRepository {
    fun getCountriesResponse() : CountriesApiService

    fun insertCountries(list: List<CountriesEntity>)

    fun getCountries(): LiveData<List<CountriesEntity>>

    fun deleteCountries()
}

class CountriesRepositoryImpl(private var cartrackApiClient: CartrackApiClient, private var countriesDao: CountriesDao): CountriesRepository {

    override fun getCountriesResponse() = cartrackApiClient.getCountriesResponse()

    override fun insertCountries(list: List<CountriesEntity>) {
        countriesDao.deleteCountries()
        countriesDao.insertCountries(list)
    }

    override fun getCountries() = countriesDao.getCountries()

    override fun deleteCountries() = countriesDao.deleteCountries()
}
