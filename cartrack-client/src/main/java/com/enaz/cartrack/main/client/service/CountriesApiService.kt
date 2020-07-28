package com.enaz.cartrack.main.client.service

import com.enaz.cartrack.main.client.model.CountriesResponse
import retrofit2.http.GET

/**
 * Created by eduardo.delito on 7/28/20.
 */
interface CountriesApiService {
    @GET("rest/v2/all")
    suspend fun getCountries(): List<CountriesResponse>
}