package ru.mole.weatherforecast.domain

import io.reactivex.Single
import ru.mole.weatherforecast.BuildConfig
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.model.CurrentDayForecast

class GetCurrentForecast(private val api: WeatherAPI)  {

    fun execute(city: String): Single<CurrentDayForecast> {
        return api.getInfoByCity(city = city, id = BuildConfig.WEATHER_API_KEY)
    }
}