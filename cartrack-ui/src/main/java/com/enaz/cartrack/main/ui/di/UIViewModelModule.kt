package com.enaz.cartrack.main.ui.di

import androidx.lifecycle.ViewModel
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.common.viewmodel.ViewModelKey
import com.enaz.cartrack.main.ui.viewmodel.CreateAccountViewModel
import com.enaz.cartrack.main.ui.viewmodel.LoginViewModel
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by eduardo.delito on 7/27/20.
 */
@Module
class UIViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideLoginViewModel(cartrackRepository: CartrackRepository): ViewModel =
        LoginViewModel(cartrackRepository)

    @Provides
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    fun provideCreateAccountViewModel(cartrackRepository: CartrackRepository): ViewModel =
        CreateAccountViewModel(cartrackRepository)

    @Provides
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun provideUsersViewModel(cartrackRepository: CartrackRepository): ViewModel =
        UsersViewModel(cartrackRepository)
}
