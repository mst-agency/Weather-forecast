package ru.mole.weatherforecast.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mole.weatherforecast.domain.model.CurrentForecast

interface WeatherAPI {

    @GET("data/2.5/weather")
    fun getInfoByCity(@Query("q") city: String, @Query("appid") id: String): Single<CurrentForecast>

}