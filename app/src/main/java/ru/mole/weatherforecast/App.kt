package ru.mole.weatherforecast

import android.app.Application
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.di.AppComponent
import ru.mole.weatherforecast.di.DaggerAppComponent
import ru.mole.weatherforecast.ui.mainScreen.MainActivity
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .weatherAPINetworkModule(WeatherAPINetworkModule())
            .build()
    }

    fun createMainActivityComponent(activity: MainActivity): MainDIComponent {
        return appComponent.createMainActivityComponent(MainDIModule(activity))
    }

    companion object {

        private lateinit var instance: App
        fun getInstance(): App {
            return instance
        }

    }
}