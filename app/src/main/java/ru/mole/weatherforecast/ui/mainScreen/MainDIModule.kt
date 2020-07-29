package ru.mole.weatherforecast.ui.mainScreen

import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.database.AppDatabase
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.ForecastTransactionDBUseCase
import ru.mole.weatherforecast.domain.GetCurrentForecast

@Module
class MainDIModule(private val view: MainContract.View) {

    @Provides
    @MainDIScreen
    fun provideMenuPresenter(api: WeatherAPI, database: AppDatabase): MainPresenter {
        val getCurrentForecast = GetCurrentForecast(api)
        val transactionDB = ForecastTransactionDBUseCase(database.dbDataForecast())

        return MainPresenter(view, getCurrentForecast, transactionDB )
    }
}