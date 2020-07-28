package ru.mole.weatherforecast.ui.detailScreen.sevenDays

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.domain.GetSeveralForecast

@Module
class SevenDaysDIModule(private val view: SevenDaysContract.View) {

    @Provides
    @SevenDaysDIScreen
    fun provideMenuPresenter(api: WeatherAPI): SevenDaysPresenter {
        val getSeveralForecast = GetSeveralForecast(api)
        return SevenDaysPresenter(view, getSeveralForecast)
    }
}