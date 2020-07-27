package com.enaz.cartrack.main.di.module

import com.enaz.cartrack.main.di.ClientModule
import com.enaz.cartrack.main.di.DBModule
import com.enaz.cartrack.main.ui.di.login.LoginViewModelModule
import dagger.Module

/**
 * Created by eduardo.delito on 7/26/20.
 */
@Module(includes = [
    DBModule::class,
    ClientModule::class
])
class CartrackModule