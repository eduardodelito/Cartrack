package com.enaz.cartrack.main.ui.mapper

import com.enaz.cartrack.main.client.model.UsersResponse
import com.enaz.cartrack.main.db.entity.UsersEntity

/**
 * Users model mapper.
 *
 * Created by eduardo.delito on 7/27/20.
 */
fun List<UsersResponse>.userResponseModelToUsersResponse() : List<UsersEntity> {
    return this.map {
        UsersEntity(
            id = it.id,
            name = it.name,
            username = it.username,
            email = it.email,
            address = it.address,
            phone = it.phone,
            website = it.website,
            company = it.company
        )
    }
}

fun List<UsersEntity>.entityModelToUsersResponse() : List<UsersResponse> {
    return this.map {
        UsersResponse(
            id = it.id,
            name = it.name,
            username = it.username,
            email = it.email,
            address = it.address,
            phone = it.phone,
            website = it.website,
            company = it.company
        )
    }
}
