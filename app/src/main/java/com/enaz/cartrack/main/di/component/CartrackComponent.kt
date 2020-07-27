package com.enaz.cartrack.main.di.component

import android.app.Application
import com.enaz.cartrack.main.di.ClientModule
import com.enaz.cartrack.main.di.DBModule
import com.enaz.cartrack.main.di.module.ActivityBindingModule
import com.enaz.cartrack.main.di.module.CartrackModule
import com.enaz.cartrack.main.di.module.ViewModelBindingModule
import com.enaz.cartrack.main.ui.CartrackApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        CartrackModule::class,
        ViewModelBindingModule::class
    ]
)
interface CartrackComponent : AndroidInjector<CartrackApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun database(dbModule: DBModule): Builder
        fun client(client: ClientModule): Builder
        fun build(): CartrackComponent
    }
}
