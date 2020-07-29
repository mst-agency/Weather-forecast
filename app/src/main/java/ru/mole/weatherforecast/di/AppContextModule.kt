package ru.mole.weatherforecast.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.App
import javax.inject.Singleton

@Module
class AppContextModule(app: App) {

    var application: App = app

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application
    }
}