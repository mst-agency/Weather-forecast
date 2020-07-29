package ru.mole.weatherforecast.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeveralDaysForecast(val lat: Double,
                               val lon: Double,
                               val timezone: String,
                               val timezone_offset: String,
                               val current: Current,
                               val minutely: List<Minutely>,
                               val hourly: List<Hourly>,
                               val daily: List<Daily>) : Parcelable

@Parcelize
data class Current(val dt: Long,
                   val sunrise: Long,
                   val sunset: Long,
                   val temp: Double,
                   val feels_like: Double,
                   val pressure: Int,
                   val humidity: Int,
                   val dew_point: Double,
                   val uvi: Double,
                   val clouds: Int,
                   val visibility: Int,
                   val wind_speed: Double,
                   val wind_deg: Int,
                   val weather: List<Weather>,
                   val rain: Rain
) : Parcelable

@Parcelize
data class Rain(@SerializedName("1h") var h1: Double) : Parcelable

@Parcelize
data class Minutely(val dt: Long,
                    val precipitation: Float) : Parcelable

@Parcelize
data class Hourly(val dt: Long,
                  val temp: Double,
                  val feels_like: Double,
                  val pressure: Int,
                  val humidity: Int,
                  val dew_point: Double,
                  val clouds: Int,
                  val visibility: Int,
                  val wind_speed: Double,
                  val wind_deg: Int,
                  val weather: List<Weather>,
                  val pop: Double,
                  val rain: Rain) : Parcelable

@Parcelize
data class Daily(val dt: Long?,
                 val sunrise: Long?,
                 val sunset: Long?,
                 val temp: Temp?,
                 val feels_like: FeelsLike?,
                 val pressure: Int?,
                 val humidity: Int?,
                 val dew_point: Double?,
                 val wind_speed: Double?,
                 val wind_deg: Int?,
                 val weather: List<Weather>?,
                 val clouds: Int?,
                 val pop: Double?,
                 val rain: Double?,
                 val uvi: Double?) : Parcelable

@Parcelize
data class Temp(val day: Double?,
                val min: Double?,
                val max: Double?,
                val night: Double?,
                val eve: Double?,
                val morn: Double?) : Parcelable

@Parcelize
data class FeelsLike(val day: Double,
                     val night: Double,
                     val eve: Double,
                     val morn: Double) : Parcelable