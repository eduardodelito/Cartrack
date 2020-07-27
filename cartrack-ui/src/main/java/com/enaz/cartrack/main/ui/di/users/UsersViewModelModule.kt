package com.enaz.cartrack.main.ui.di.users

import androidx.lifecycle.ViewModel
import com.enaz.cartrack.main.common.viewmodel.ViewModelKey
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
class UsersViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun provideUsersViewModel(): ViewModel =
        UsersViewModel()
}
