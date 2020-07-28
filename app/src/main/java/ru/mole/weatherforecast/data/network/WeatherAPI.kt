package ru.mole.weatherforecast.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.SeveralDaysForecast

interface WeatherAPI {

    @GET("data/2.5/weather")
    fun getInfoByCity(@Query("q") city: String, @Query("appid") id: String): Single<CurrentDayForecast>

    @GET("data/2.5/onecall")
    fun getInfoByCityForSeveralDays(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") id: String): Single<SeveralDaysForecast>

}