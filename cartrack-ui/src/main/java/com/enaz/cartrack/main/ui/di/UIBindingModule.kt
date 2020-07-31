package com.enaz.cartrack.main.ui.di

import androidx.lifecycle.ViewModelProvider
import com.enaz.cartrack.main.ui.fragment.*
import com.enaz.cartrack.main.ui.viewmodel.*
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Module class to bind fragment with provide init of view model factory.
 *
 * Created by eduardo.delito on 7/27/20.
 */
@Module
abstract class UIBindingModule {
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

    @ContributesAndroidInjector(modules = [InjectUsersViewModelModule::class])
    abstract fun bindUsersFragment(): UsersFragment

    @Module
    class InjectUsersViewModelModule {
        @Provides
        internal fun provideUsersViewModel(
            factory: ViewModelProvider.Factory,
            target: UsersFragment
        ) = ViewModelProvider(target, factory).get(UsersViewModel::class.java)
    }

    @ContributesAndroidInjector(modules = [InjectDetailsModelModule::class])
    abstract fun bindDetailsFragment(): DetailsFragment

    @Module
    class InjectDetailsModelModule {
        @Provides
        internal fun provideDetailsViewModel(
            factory: ViewModelProvider.Factory,
            target: DetailsFragment
        ) = ViewModelProvider(target, factory).get(DetailsViewModel::class.java)
    }
}
