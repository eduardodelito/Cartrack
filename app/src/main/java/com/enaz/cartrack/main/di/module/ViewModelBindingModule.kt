package com.enaz.cartrack.main.di.module

import androidx.lifecycle.ViewModelProvider
import com.enaz.cartrack.main.common.viewmodel.ViewModelFactory
import com.enaz.cartrack.main.ui.di.login.LoginViewModelModule
import com.enaz.cartrack.main.ui.di.users.UsersViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module(
    includes = [
        LoginViewModelModule::class,
        UsersViewModelModule::class
    ]
)
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
