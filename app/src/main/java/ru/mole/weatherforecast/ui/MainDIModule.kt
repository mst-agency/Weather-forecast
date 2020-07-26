package ru.mole.weatherforecast.ui

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.GetCurrentForecast

@Module
class MainDIModule(private val view: MainContract.View) {

    @Provides
    @MainDIScreen
    fun provideMenuPresenter(api: WeatherAPI): MainPresenter {
        val getCurrentForecast = GetCurrentForecast(api)
        return MainPresenter(view, getCurrentForecast)
    }
}