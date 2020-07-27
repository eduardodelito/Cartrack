package com.enaz.cartrack.main

import com.enaz.cartrack.main.client.UsersResponse
import retrofit2.http.GET

/**
 * Created by eduardo.delito on 7/26/20.
 */
interface CartrackApiService {
    @GET("users/")
    suspend fun getUsersResponse(): List<UsersResponse>
}
