package com.enaz.cartrack.main.di

import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.repository.*
import com.enaz.cartrack.main.db.dao.AccountDao
import com.enaz.cartrack.main.db.dao.CountriesDao
import com.enaz.cartrack.main.db.dao.UsersDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Client Module
 *
 * Created by eduardo.delito on 7/26/20.
 */
@Module
class ClientModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideCartrackApiClient(okHttpClient: OkHttpClient.Builder) =
        CartrackApiClient(okHttpClient)

    @Provides
    @Singleton
    fun provideCartrackRepository(accountDao: AccountDao): AccountRepository =
        AccountRepositoryImpl(accountDao)

    @Provides
    @Singleton
    fun provideUsersRepository(
        cartrackApiClient: CartrackApiClient,
        usersDao: UsersDao
    ): UsersRepository =
        UsersRepositoryImpl(cartrackApiClient, usersDao)

    @Provides
    @Singleton
    fun provideCountriesRepository(cartrackApiClient: CartrackApiClient, countriesDao: CountriesDao): CountriesRepository =
        CountriesRepositoryImpl(cartrackApiClient, countriesDao)
}
