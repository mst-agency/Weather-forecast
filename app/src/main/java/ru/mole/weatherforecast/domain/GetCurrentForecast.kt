package ru.mole.weatherforecast.domain

import io.reactivex.Single
import ru.mole.weatherforecast.BuildConfig
import ru.mole.weatherforecast.data.network.WeatherAPI

class GetCurrentForecast(private val api: WeatherAPI)  {

    fun execute(): Single<String> {
        return api.getInfoByCity(zip = "94040,us", id = BuildConfig.WEATHER_API_KEY)
    }
}