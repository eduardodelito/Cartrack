package com.enaz.cartrack.main.ui

import com.enaz.cartrack.main.di.ClientModule
import com.enaz.cartrack.main.di.DBModule
import com.enaz.cartrack.main.di.component.DaggerCartrackComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by eduardo.delito on 7/26/20.
 */
class CartrackApplication: DaggerApplication() {
    /**
     * Dagger injection for all modules.
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val context = applicationContext
        return DaggerCartrackComponent
            .builder()
            .application(this)
            .database(DBModule(this))
            .client(ClientModule())
            .build()
    }
}
