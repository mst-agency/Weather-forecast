package ru.mole.weatherforecast.domain

import io.reactivex.Single
import ru.mole.weatherforecast.BuildConfig
import ru.mole.weatherforecast.data.network.WeatherAPI
import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.SeveralDaysForecast

class GetSeveralForecast(private val api: WeatherAPI)  {

    fun execute(coord: Coordinate): Single<SeveralDaysForecast> {
        return api.getInfoByCityForSeveralDays(lat = coord.lat, lon = coord.lon, id = BuildConfig.WEATHER_API_KEY)
    }
}