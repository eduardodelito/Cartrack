package com.enaz.cartrack.main.ui.di.users

import androidx.lifecycle.ViewModelProvider
import com.enaz.cartrack.main.ui.fragment.UsersFragment
import com.enaz.cartrack.main.ui.viewmodel.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
abstract class UsersBindingModule {
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
}
