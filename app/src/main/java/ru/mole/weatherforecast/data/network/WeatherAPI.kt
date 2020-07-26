package ru.mole.weatherforecast.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface WeatherAPI {

    @GET("data/2.5/weather")
    @Headers("Content-Type: application/json")
    fun getInfoByCity(@Header("q") city: String = "Omsk"): Observable<String>

}