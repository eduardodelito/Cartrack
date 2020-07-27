package com.enaz.cartrack.main.di

import com.enaz.cartrack.main.client.CartrackApiClient
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.client.repository.CartrackRepositoryImpl
import com.enaz.cartrack.main.db.dao.AccountDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
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
    fun provideCartrackApiClient(okHttpClient: OkHttpClient.Builder) = CartrackApiClient(okHttpClient)

    @Provides
    @Singleton
    fun provideCartrackRepository(
        cartrackApiClient: CartrackApiClient,
        accountDao: AccountDao): CartrackRepository =
        CartrackRepositoryImpl(cartrackApiClient, accountDao)
}
