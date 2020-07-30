package com.enaz.cartrack.main.client.repository

import androidx.lifecycle.LiveData
import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.service.CountriesApiService
import com.enaz.cartrack.main.db.dao.CountriesDao
import com.enaz.cartrack.main.db.entity.CountriesEntity
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * CountriesRepository class for saving and querying data from db.
 *
 * Created by eduardo.delito on 7/28/20.
 */
interface CountriesRepository {

    /**
     * Method for countries api service.
     */
    fun getCountriesResponse() : CountriesApiService

    /**
     * Method to insert list of countries.
     * @param list
     */
    fun insertCountries(list: List<CountriesEntity>)

    /**
     * Method for the live data list of countries.
     */
    fun getCountries(): LiveData<List<CountriesEntity>>

    /**
     * Method to delete countries.
     */
    fun deleteCountries()
}

class CountriesRepositoryImpl(private var cartrackApiClient: CartrackApiClient, private var countriesDao: CountriesDao): CountriesRepository {

    /**
     * Method for countries api service.
     */
    override fun getCountriesResponse() = cartrackApiClient.getCountriesResponse()

    /**
     * Method to insert list of countries.
     * @param list
     */
    override fun insertCountries(list: List<CountriesEntity>) {
        countriesDao.deleteCountries()
        countriesDao.insertCountries(list)
    }

    /**
     * Method for the live data list of countries.
     */
    override fun getCountries() = countriesDao.getCountries()

    /**
     * Method to delete countries.
     */
    override fun deleteCountries() = countriesDao.deleteCountries()
}
