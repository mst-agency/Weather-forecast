package ru.mole.weatherforecast.ui.detailScreen.currentDay

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.domain.GetSeveralForecast

@Module
class CurrentDayDIModule(private val view: CurrentDayContract.View) {

    @Provides
    @CurrentDayDIScreen
    fun provideMenuPresenter(): CurrentDayPresenter {
        return CurrentDayPresenter(view)
    }
}