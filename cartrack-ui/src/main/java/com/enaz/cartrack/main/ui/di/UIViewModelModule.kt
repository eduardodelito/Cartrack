package com.enaz.cartrack.main.ui.di

import androidx.lifecycle.ViewModel
import com.enaz.cartrack.main.client.repository.AccountRepository
import com.enaz.cartrack.main.client.repository.CountriesRepository
import com.enaz.cartrack.main.client.repository.UsersRepository
import com.enaz.cartrack.main.common.viewmodel.ViewModelKey
import com.enaz.cartrack.main.ui.viewmodel.DetailsViewModel
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
    fun provideLoginViewModel(accountRepository: AccountRepository): ViewModel =
        LoginViewModel(accountRepository)

    @Provides
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    fun provideCreateAccountViewModel(accountRepository: AccountRepository, countriesRepository: CountriesRepository): ViewModel =
        CreateAccountViewModel(accountRepository, countriesRepository)

    @Provides
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun provideUsersViewModel(usersRepository: UsersRepository): ViewModel =
        UsersViewModel(usersRepository)

    @Provides
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun provideDetailsViewModel(): ViewModel =
        DetailsViewModel()
}
