package ru.mole.weatherforecast.ui.detailScreen.threeDays

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.domain.GetSeveralForecast

@Module
class ThreeDaysDIModule(private val view: ThreeDaysContract.View) {

    @Provides
    @ThreeDaysDIScreen
    fun provideMenuPresenter(api: WeatherAPI): ThreeDaysPresenter {
        val getSeveralForecast = GetSeveralForecast(api)
        return ThreeDaysPresenter(view, getSeveralForecast)
    }
}