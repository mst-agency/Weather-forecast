package ru.mole.weatherforecast.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DetailForecast")
class DataForecastDetail(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    var id: Int,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "temp_max")
    val tempMax: Double,

    @ColumnInfo(name = "temp_min")
    val tempMin: Double,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lon")
    val lon: Double

)