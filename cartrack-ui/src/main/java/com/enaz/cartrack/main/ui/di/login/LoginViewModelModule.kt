package com.enaz.cartrack.main.ui.di.login

import androidx.lifecycle.ViewModel
import com.enaz.cartrack.main.client.repository.CartrackRepository
import com.enaz.cartrack.main.common.viewmodel.ViewModelKey
import com.enaz.cartrack.main.ui.viewmodel.CreateAccountViewModel
import com.enaz.cartrack.main.ui.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
class LoginViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideLoginViewModel(): ViewModel =
        LoginViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    fun provideCreateAccountViewModel(cartrackRepository: CartrackRepository): ViewModel =
        CreateAccountViewModel(cartrackRepository)
}
