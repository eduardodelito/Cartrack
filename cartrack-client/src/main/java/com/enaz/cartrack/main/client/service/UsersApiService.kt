package com.enaz.cartrack.main.client.service

import com.enaz.cartrack.main.client.model.UsersResponse
import retrofit2.http.GET

/**
 * Created by eduardo.delito on 7/28/20.
 */
interface UsersApiService {
    @GET("users/")
    suspend fun getUsersResponse(): List<UsersResponse>
}