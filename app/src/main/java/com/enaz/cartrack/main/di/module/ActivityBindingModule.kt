package com.enaz.cartrack.main.di.module

import com.enaz.cartrack.main.ui.MainActivity
import com.enaz.cartrack.main.ui.di.UIBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [
        UIBindingModule::class
    ])
    abstract fun bindMainActivity(): MainActivity
}
