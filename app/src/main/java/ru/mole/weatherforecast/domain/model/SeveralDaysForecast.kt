package ru.mole.weatherforecast.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeveralDaysForecast(val city: City,
                               val cod: Int,
                               val message: Float,
                               val cnt: Int,
                               val list: List<ForecastCityModel>) : Parcelable

@Parcelize
data class City(val id: Long,
                val name: String,
                val coord: Coordinate,
                val country: String,
                val timezone: Int) : Parcelable

@Parcelize
data class ForecastCityModel(val dt: Long,
                             val sunrise: Long,
                             val sunset: Long,
                             val temp: Temp,
                             val feels_like: FeelsLike,
                             val pressure: Double,
                             val humidity: Int,
                             val weather: List<Weather>,
                             val speed: Double,
                             val deg: Int,
                             val clouds: Int) : Parcelable

@Parcelize
data class Temp(val day: Double,
                val min: Double,
                val max: Double,
                val night: Double,
                val eve: Double,
                val morn: Int) : Parcelable

@Parcelize
data class FeelsLike(val day: Double,
                     val night: Double,
                     val eve: Double,
                     val morn: Int) : Parcelable