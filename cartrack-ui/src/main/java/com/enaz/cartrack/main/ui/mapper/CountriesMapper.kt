package com.enaz.cartrack.main.ui.mapper

import com.enaz.cartrack.main.client.model.CountriesResponse
import com.enaz.cartrack.main.db.entity.CountriesEntity

/**
 * Countries model mapper.
 *
 * Created by eduardo.delito on 7/28/20.
 */

fun List<CountriesResponse>.countriesModelToCountriesEntity() : List<CountriesEntity> {
    return this.map {
        CountriesEntity(
            id = 0,
            alpha2Code = it.alpha2Code,
            name = it.name,
            flag = it.flag
        )
    }
}

fun List<CountriesEntity>.countriesEntityToCountriesResponse() : List<CountriesResponse> {
    return this.map {
        CountriesResponse(
            alpha2Code = it.alpha2Code,
            name = it.name,
            flag = it.flag
        )
    }
}
