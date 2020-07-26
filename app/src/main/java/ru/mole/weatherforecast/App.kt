package ru.mole.weatherforecast

import android.app.Application
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.di.AppComponent
import ru.mole.weatherforecast.di.DaggerAppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .weatherAPINetworkModule(WeatherAPINetworkModule())
            .build()
    }
}