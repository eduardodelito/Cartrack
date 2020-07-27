package com.enaz.cartrack.main.ui.di.login

import androidx.lifecycle.ViewModelProvider
import com.enaz.cartrack.main.ui.fragment.CreateAccountFragment
import com.enaz.cartrack.main.ui.fragment.LoginFragment
import com.enaz.cartrack.main.ui.viewmodel.CreateAccountViewModel
import com.enaz.cartrack.main.ui.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
abstract class LoginBindingModule {

    @ContributesAndroidInjector(modules = [InjectLoginViewModelModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @Module
    class InjectLoginViewModelModule {
        @Provides
        internal fun provideLoginViewModel(
            factory: ViewModelProvider.Factory,
            target: LoginFragment
        ) = ViewModelProvider(target, factory).get(LoginViewModel::class.java)
    }

    @ContributesAndroidInjector(modules = [InjectCreateAccountViewModelModule::class])
    abstract fun bindCreateAccountFragment(): CreateAccountFragment

    @Module
    class InjectCreateAccountViewModelModule {
        @Provides
        internal fun provideCreateAccountViewModel(
            factory: ViewModelProvider.Factory,
            target: CreateAccountFragment
        ) = ViewModelProvider(target, factory).get(CreateAccountViewModel::class.java)
    }
}
