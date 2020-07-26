package ru.mole.weatherforecast.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather")
    fun getInfoByCity(@Query("zip") zip: String, @Query("appid") id: String): Single<String>

}