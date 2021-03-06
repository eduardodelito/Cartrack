package com.enaz.cartrack.main.client

import com.enaz.cartrack.main.client.service.CountriesApiService
import com.enaz.cartrack.main.client.service.UsersApiService
import com.enaz.cartrack.main.db.BuildConfig
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class to initialize Retrofit.
 *
 * Created by eduardo.delito on 7/26/20.
 */
class CartrackApiClient(okHttp: OkHttpClient.Builder) : Interceptor, Authenticator {

    private var retrofitForUsers: Retrofit
    private var retrofitForCountries: Retrofit

    init {
        okHttp.addInterceptor(this)
        okHttp.authenticator(this)

        retrofitForUsers = Retrofit.Builder()
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_HTTP_URL)
            .build()

        retrofitForCountries = Retrofit.Builder()
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.COUNTRY_BASE_HTTP_URL)
            .build()
    }

    /**
     *  method to intercept request.
     *  @param chain interceptor.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, HEADER_VALUE_APP_JSON)
            .addHeader(HEADER_ACCEPT, HEADER_VALUE_APP_JSON)
            .build()
        return chain.proceed(request)
    }

    /**
     * Request authenticator
     * @param route
     * @param response
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request().newBuilder().build()
    }

    /**
     * Instance call for the retrofit service.
     */
    fun getUsersResponse(): UsersApiService {
        return retrofitForUsers.create(UsersApiService::class.java)
    }

    /**
     * Instance call for the retrofit service.
     */
    fun getCountriesResponse(): CountriesApiService {
        return retrofitForCountries.create(CountriesApiService::class.java)
    }

    companion object {
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_ACCEPT = "Accept"
        const val HEADER_VALUE_APP_JSON = "application/json"
    }
}
