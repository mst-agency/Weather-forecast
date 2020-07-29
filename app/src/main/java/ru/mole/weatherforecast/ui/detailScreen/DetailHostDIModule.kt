package ru.mole.weatherforecast.ui.detailScreen

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.database.AppDatabase
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.DetailTransactionDBUseCase
import ru.mole.weatherforecast.domain.ForecastTransactionDBUseCase
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.domain.GetSeveralForecast

@Module
class DetailHostDIModule(private val view: DetailHostContract.View) {

    @Provides
    @DetailHostDIScreen
    fun provideMenuPresenter(api: WeatherAPI, database: AppDatabase): DetailHostPresenter {
        val getCurrentForecast = GetCurrentForecast(api)
        val transactionDB = DetailTransactionDBUseCase(database.dbDataDetailForecast())
        val getSeveralForecast = GetSeveralForecast(api)

        return DetailHostPresenter(view, getSeveralForecast, transactionDB)
    }
}